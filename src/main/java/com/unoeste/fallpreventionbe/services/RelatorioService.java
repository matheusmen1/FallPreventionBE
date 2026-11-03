package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.dto.ConcluirSessaoDTO;
import com.unoeste.fallpreventionbe.dto.RelatorioEscalaDTO;
import com.unoeste.fallpreventionbe.dto.RelatorioFaseDTO;
import com.unoeste.fallpreventionbe.dto.RelatorioSessaoDTO;
import com.unoeste.fallpreventionbe.dto.SalvarEscalaDTO;
import com.unoeste.fallpreventionbe.entities.*;
import com.unoeste.fallpreventionbe.repositories.ResultadoSessaoFaseEscalaRepository;
import com.unoeste.fallpreventionbe.repositories.ResultadoSessaoFaseRepository;
import com.unoeste.fallpreventionbe.repositories.ResultadoSessaoRepository;
import com.unoeste.fallpreventionbe.repositories.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RelatorioService
{
    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private ResultadoSessaoRepository resultadoSessaoRepository;

    @Autowired
    private ResultadoSessaoFaseRepository resultadoSessaoFaseRepository;

    @Autowired
    private ResultadoSessaoFaseEscalaRepository resultadoSessaoFaseEscalaRepository;

    @Transactional
    public RelatorioSessaoDTO buscarRelatorioPorSessao(Long sesId)
    {
        Sessao sessao = sessaoRepository.findById(sesId).orElse(null);
        if (sessao == null) {
            return null;
        }

        ResultadoSessao resultadoSessao = resultadoSessaoRepository.findBySessaoId(sesId).orElse(null);
        if (resultadoSessao == null) {
            resultadoSessao = new ResultadoSessao();
            resultadoSessao.setId(null);
            resultadoSessao.setSessao(sessao);
            LocalDateTime inicio = sessao.getData_hora() != null ? sessao.getData_hora() : LocalDateTime.now();
            resultadoSessao.setDataHoraInicio(inicio);
            resultadoSessao.setDataHoraFim(inicio.plusMinutes(30));
            resultadoSessao.setDuracaoTotalMinutos(30);
            resultadoSessao.setObservacao(
                    sessao.getObservacao() != null && !sessao.getObservacao().isEmpty()
                            ? sessao.getObservacao()
                            : "Sessão concluída."
            );
            resultadoSessao = resultadoSessaoRepository.save(resultadoSessao);
        }

        String textoObservacaoSessao = (sessao.getSessaoObservacaos() != null && sessao.getSessaoObservacaos().getObservacao() != null)
                ? sessao.getSessaoObservacaos().getObservacao()
                : "";

        List<SessaoFase> sessaoFases = sessao.getSessaoFases() != null
                ? new ArrayList<>(sessao.getSessaoFases())
                : new ArrayList<>();
        sessaoFases.sort(Comparator.comparing(
                sf -> sf.getOrdem() != null ? sf.getOrdem() : Integer.MAX_VALUE
        ));

        List<RelatorioFaseDTO> fasesDTO = new ArrayList<>();

        for (SessaoFase sf : sessaoFases) {
            ResultadoSessaoFase rsf = resultadoSessaoFaseRepository
                    .findFirstBySessaoFase_IdOrderByIdDesc(sf.getId())
                    .orElse(null);

            if (rsf == null) {
                rsf = new ResultadoSessaoFase();
                rsf.setId(null);
                rsf.setSessaoFase(sf);
                rsf.setRepeticoesRealizadas(sf.getRepeticao() != null ? sf.getRepeticao() : 0);
                rsf.setTempoRealizado(sf.getTempo() != null ? sf.getTempo() : 0f);
                LocalDateTime faseInicio = resultadoSessao.getDataHoraInicio() != null
                        ? resultadoSessao.getDataHoraInicio()
                        : LocalDateTime.now();
                rsf.setDataHoraInicio(faseInicio);
                rsf.setDataHoraFim(faseInicio.plusMinutes(10));
                rsf = resultadoSessaoFaseRepository.save(rsf);
            }

            ResultadoSessaoFaseEscala escalaEntity = resultadoSessaoFaseEscalaRepository
                    .findByResultadoSessaoFase_Id(rsf.getId())
                    .orElse(rsf.getEscala());

            RelatorioEscalaDTO escalaDTO = null;
            if (escalaEntity != null) {
                escalaDTO = new RelatorioEscalaDTO(
                        escalaEntity.getId(),
                        escalaEntity.getNomeEscala(),
                        escalaEntity.getItemAvaliado(),
                        escalaEntity.getPontuacao(),
                        escalaEntity.getJustificativa()
                );
            }

            String exercicioNome = sf.getExercicio() != null ? sf.getExercicio().getNome() : "Intervenção Clínica";
            String exercicioDescricao = sf.getExercicio() != null ? sf.getExercicio().getDescricao() : "";

            String observacaoFase = extrairObservacaoDaFase(textoObservacaoSessao, exercicioNome);
            if ((observacaoFase == null || observacaoFase.isBlank()) && escalaEntity != null && escalaEntity.getJustificativa() != null && !escalaEntity.getJustificativa().isBlank()) {
                observacaoFase = escalaEntity.getJustificativa();
            }

            RelatorioFaseDTO faseDTO = new RelatorioFaseDTO(
                    rsf.getId(),
                    sf.getId(),
                    sf.getOrdem(),
                    exercicioNome,
                    exercicioDescricao,
                    sf.getRepeticao(),
                    sf.getTempo(),
                    sf.getIs_repeticao(),
                    rsf.getRepeticoesRealizadas(),
                    rsf.getTempoRealizado(),
                    rsf.getDataHoraInicio(),
                    rsf.getDataHoraFim(),
                    observacaoFase,
                    escalaDTO
            );

            fasesDTO.add(faseDTO);
        }

        String pacienteNome = sessao.getPaciente() != null ? sessao.getPaciente().getNome() : "Não Informado";
        String fisioterapeutaNome = sessao.getResponsavel() != null ? sessao.getResponsavel().getNome() : "Não Informado";
        String sessaoStatus = sessao.getStatus() != null ? sessao.getStatus() : "APROVADA";

        return new RelatorioSessaoDTO(
                sessao.getId(),
                resultadoSessao.getId(),
                sessaoStatus,
                pacienteNome,
                fisioterapeutaNome,
                resultadoSessao.getDataHoraInicio(),
                resultadoSessao.getDataHoraFim(),
                resultadoSessao.getDuracaoTotalMinutos(),
                resultadoSessao.getObservacao(),
                fasesDTO
        );
    }

    private String extrairObservacaoDaFase(String textoObservacaoSessao, String exercicioNome)
    {
        if (textoObservacaoSessao == null || textoObservacaoSessao.isBlank() || exercicioNome == null) {
            return null;
        }
        String marcador = "(" + exercicioNome + "):";
        StringBuilder sb = new StringBuilder();
        for (String linha : textoObservacaoSessao.split("\\r?\\n")) {
            int idx = linha.indexOf(marcador);
            if (idx >= 0) {
                String trecho = linha.substring(idx + marcador.length()).trim();
                if (!trecho.isEmpty()) {
                    if (sb.length() > 0) sb.append(" | ");
                    sb.append(trecho);
                }
            }
        }
        return sb.length() > 0 ? sb.toString() : null;
    }

    @Transactional
    public RelatorioEscalaDTO salvarEscalaFase(Long resfId, SalvarEscalaDTO dto)
    {
        ResultadoSessaoFase rsf = resultadoSessaoFaseRepository.findById(resfId).orElse(null);
        if (rsf == null) {
            return null;
        }

        Integer pontuacao = dto.getPontuacao();
        if (pontuacao == null || pontuacao < 0 || pontuacao > 4) {
            throw new IllegalArgumentException("A pontuação deve estar entre 0 e 4.");
        }

        ResultadoSessaoFaseEscala escala = resultadoSessaoFaseEscalaRepository
                .findByResultadoSessaoFase_Id(resfId)
                .orElse(null);

        if (escala == null) {
            escala = new ResultadoSessaoFaseEscala();
            escala.setId(null);
            escala.setResultadoSessaoFase(rsf);
        }

        String nomeEscala = (dto.getNomeEscala() != null && !dto.getNomeEscala().trim().isEmpty())
                ? dto.getNomeEscala().trim()
                : "Escala de Berg";

        String itemAvaliado = dto.getItemAvaliado();
        if (itemAvaliado == null || itemAvaliado.trim().isEmpty()) {
            itemAvaliado = "Não se aplica";
        }

        escala.setNomeEscala(nomeEscala);
        escala.setItemAvaliado(itemAvaliado.trim());
        escala.setPontuacao(pontuacao);
        if (dto.getJustificativa() != null && !dto.getJustificativa().trim().isEmpty()) {
            escala.setJustificativa(dto.getJustificativa().trim());
        } else if (escala.getJustificativa() == null) {
            escala.setJustificativa("");
        }

        ResultadoSessaoFaseEscala salvo = resultadoSessaoFaseEscalaRepository.save(escala);

        return new RelatorioEscalaDTO(
                salvo.getId(),
                salvo.getNomeEscala(),
                salvo.getItemAvaliado(),
                salvo.getPontuacao(),
                salvo.getJustificativa()
        );
    }

    @Transactional
    public RelatorioSessaoDTO concluirSessao(Long sesId, ConcluirSessaoDTO dto)
    {
        Sessao sessao = sessaoRepository.findById(sesId).orElse(null);
        if (sessao == null) {
            return null;
        }

        sessao.setStatus("CONCLUIDA");
        sessaoRepository.save(sessao);

        ResultadoSessao resultadoSessao = resultadoSessaoRepository.findBySessaoId(sesId).orElse(null);
        if (resultadoSessao != null && dto != null && dto.getObservacao() != null && !dto.getObservacao().trim().isEmpty()) {
            resultadoSessao.setObservacao(dto.getObservacao().trim());
            resultadoSessaoRepository.save(resultadoSessao);
        }

        return buscarRelatorioPorSessao(sesId);
    }
}
