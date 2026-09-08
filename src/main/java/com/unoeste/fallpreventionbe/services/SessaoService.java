package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.*;
import com.unoeste.fallpreventionbe.repositories.SessaoRepository;
import com.unoeste.fallpreventionbe.repositories.UsuarioRepository;
import com.unoeste.fallpreventionbe.webSocket.SessaoWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SessaoService
{
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SessaoWebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Sessao save(Sessao sessao)
    {
        try
        {
            List<SessaoFase> sessaoFases = sessao.getSessaoFases();

            if (sessao.getId() != null)
            {
                sessaoRepository.deleteSessaoFase(sessao.getId());
            }

            //sessao.setData_hora(LocalDateTime.now());
            if (sessao.getResponsavel().getNivel() > 0)
                sessao.setStatus("APROVADA");
            else
                sessao.setStatus("PENDENTE");
            sessao.setSessaoFases(null);
            sessao.setAprovacaoSessao(null);
            sessao.setResultadoSessao(null);

            Sessao novaSessao = sessaoRepository.save(sessao);



            for (SessaoFase sessaoFase : sessaoFases)
                sessaoRepository.addSessaoFase(sessaoFase.getOrdem(), sessaoFase.getRepeticao(), sessaoFase.getTempo(), sessaoFase.getIs_repeticao(), sessao.getId(),sessaoFase.getExercicio().getId());
            return novaSessao;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Sessao getById(Long id)
    {
        return sessaoRepository.findById(id).orElse(null);
    }

    public List<Sessao> getAllByPaciente(Long id)
    {
        List<Sessao> sessaos = sessaoRepository.getAllByPaciente(id);
        return sessaos;
    }

    public Sessao cancelarSessaoPendente(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("CANCELADA");
            sessaoRepository.save(sessao);
            return sessao;
        }
        return null;
    }

    public AprovacaoSessao aprovarSessaoPendente(AprovacaoSessao aprovacaoSessao, Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {

                Usuario fisio = usuarioRepository.findById(aprovacaoSessao.getFisioterapeuta().getId()).orElse(null);

                if (fisio != null && fisio.getNivel() > 0)
                {
                    aprovacaoSessao.setData_hora(LocalDateTime.now());
                    aprovacaoSessao.setSessao(sessao);
                    aprovacaoSessao.setFisioterapeuta(fisio);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
                    if (sessao.getAprovacaoSessao() != null)
                    {
                        AprovacaoSessao aprovacaoSessaoAntigo = sessao.getAprovacaoSessao();
                        String motivoConcatenado = aprovacaoSessaoAntigo.getMotivo() +"\n"+
                                aprovacaoSessao.getData_hora().format(formatter)+", "+ aprovacaoSessao.getMotivo();
                        aprovacaoSessao.setMotivo(motivoConcatenado);
                    }
                    else
                    {
                        aprovacaoSessao.setMotivo(aprovacaoSessao.getData_hora().format(formatter) +", " + aprovacaoSessao.getMotivo());
                    }

                    sessao.setStatus(aprovacaoSessao.getStatus().toUpperCase());
                    sessaoRepository.save(sessao);
                    if (sessao.getAprovacaoSessao() == null)
                        sessaoRepository.addAprovacaoSessao(aprovacaoSessao.getData_hora(), aprovacaoSessao.getFisioterapeuta().getId(), aprovacaoSessao.getStatus(), aprovacaoSessao.getMotivo(), sessao.getId());
                    else
                        sessaoRepository.updateAprovacaoSessao(aprovacaoSessao.getData_hora(), aprovacaoSessao.getFisioterapeuta().getId(), aprovacaoSessao.getStatus(), aprovacaoSessao.getMotivo(), sessao.getId());

                    return aprovacaoSessao;
                }
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }

    public ResultadoSessao addResultadoSessao(ResultadoSessao resultadoSessao, Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                sessao.setStatus("CONCLUIDA");
                sessaoRepository.save(sessao);
                sessaoRepository.addResultadoSessao(resultadoSessao.getDuracao(), resultadoSessao.getObservacao(), sessao.getId());
                return resultadoSessao;
            }
            return null;
        }catch (Exception e)
        {
            return null;
        }
    }

    public List<Sessao> getAll()
    {
        return sessaoRepository.findAll();
    }

    public boolean delete(Long id)
    {
        try {
            if (sessaoRepository.existsById(id))
            {

                sessaoRepository.deleteSessaoObservacaoAll(id);
                sessaoRepository.deleteSessaoGravacaooAll(id);
                sessaoRepository.deleteResultadoSessao(id);
                sessaoRepository.deleteAprovacaoSessao(id);
                sessaoRepository.deleteSessaoFase(id);
                sessaoRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean deleteObservacao(Long id)
    {
        try {

            sessaoRepository.deleteSessaoObservacao(id);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public List<Sessao> getAllByUsuarioId(Long id)
    {
        return sessaoRepository.getAllUsuarioById(id);
    }

    public List<Sessao> getAllByStatusById(String status, Long id)
    {
        return sessaoRepository.getAllByStatusById(status, id);
    }

    public List<Sessao> getAllPendenteByFisioterapeutaId(Long id)
    {
        return sessaoRepository.getAllPendenteByFisioterapeutaId(id);
    }

    public List<Sessao> getAllByStatus(String status)
    {
        return sessaoRepository.getAllByStatus(status);
    }

    public boolean iniciarSessao(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("EM_ANDAMENTO");
            sessao.setOrdemAtual(1);
            Integer repeticao = null;
            Exercicio exercicio = null;
            boolean isRepeticao = true;
            Float tempo = null;
            for (int i = 0; i < sessao.getSessaoFases().size(); i++)
            {
                if (sessao.getSessaoFases().get(i).getOrdem() == sessao.getOrdemAtual())
                {
                    exercicio = sessao.getSessaoFases().get(i).getExercicio();
                    repeticao = sessao.getSessaoFases().get(i).getRepeticao();
                    tempo = sessao.getSessaoFases().get(i).getTempo();
                    isRepeticao = sessao.getSessaoFases().get(i).getIs_repeticao();
                }
            }
            String comandoJson = String.format("{\"acao\": \"INICIAR_FASE\", \"pacote_unity\": \"%s\", \"repeticao\": \"%d\", \"tempo\": \"%f\", \"is_repeticao\": \"%s\", \"sessao_id\": %d}", exercicio.getCodigo_nome().trim(), repeticao, tempo, isRepeticao, sessao.getId());

            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }

        }
        return false;
    }

    public Sessao sairSala(Long id)
    {
        try
        {
            Sessao novaSessao = sessaoRepository.findById(id).orElse(null);
            if (novaSessao != null)
            {
                novaSessao.setStatus("APROVADA");
                novaSessao.setOrdemAtual(0);
                sessaoRepository.save(novaSessao);
                return novaSessao;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean proximaFase(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            if (sessao.getOrdemAtual() < sessao.getSessaoFases().size())
            {
                sessao.setOrdemAtual(sessao.getOrdemAtual() + 1);
                Exercicio exercicio = null;
                Integer repeticao = null;
                Float tempo = null;
                boolean isRepeticao = true;
                Long sessao_id = null;
                for (int i = 0; i < sessao.getSessaoFases().size(); i++)
                {
                    if (sessao.getSessaoFases().get(i).getOrdem() == sessao.getOrdemAtual())
                    {
                        exercicio = sessao.getSessaoFases().get(i).getExercicio();
                        repeticao = sessao.getSessaoFases().get(i).getRepeticao();
                        tempo = sessao.getSessaoFases().get(i).getTempo();
                        isRepeticao = sessao.getSessaoFases().get(i).getIs_repeticao();
                        sessao_id = sessao.getId();
                    }
                }
                String comandoJson = String.format("{\"acao\": \"PROXIMA_FASE\", \"pacote_unity\": \"%s\", \"repeticao\": \"%d\", \"tempo\": \"%f\", \"is_repeticao\": \"%s\", \"sessao_id\": %d}", exercicio.getCodigo_nome().trim(), repeticao, tempo, isRepeticao, sessao_id);
                if (webSocketHandler.enviarComandoParaUnity(comandoJson))
                {
                    sessaoRepository.save(sessao);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pausarFase(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("PAUSADA");
            String comandoJson = String.format("{\"acao\": \"PAUSAR_FASE\", \"pacote_unity\": \"%s\", \"sessao_id\": %d}", null, null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }
        }
        return false;
    }
    public boolean retomarFase(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("EM_ANDAMENTO");
            String comandoJson = String.format("{\"acao\": \"RETOMAR_FASE\", \"pacote_unity\": \"%s\", \"sessao_id\": %d}", null, null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }

        }
        return false;
    }
    public boolean finalizarSessao(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            String comandoJson = String.format("{\"acao\": \"FINALIZAR_SESSAO\", \"pacote_unity\": \"%s\", \"sessao_id\": %d}", null, id);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }
        }
        return false;
    }

    public SessaoObservacao addObservacao(SessaoObservacao sessaoObservacao, Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                sessaoObservacao.setSessao(sessao);
                sessaoObservacao.setData_hora(LocalDateTime.now());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
                if (sessao.getSessaoObservacaos() == null)
                {

                    if (sessaoObservacao.getSessaoFase() != null)
                    {
                        sessaoObservacao.setObservacao(sessaoObservacao.getData_hora().format(formatter)+" ("+sessaoObservacao.getSessaoFase().getExercicio().getNome()+")"+": "+sessaoObservacao.getObservacao());
                        sessaoRepository.addObservacao(sessaoObservacao.getObservacao(), sessaoObservacao.getData_hora(), sessaoObservacao.getSessaoFase().getId(), id);
                    }
                    else
                    {
                        sessaoObservacao.setObservacao(sessaoObservacao.getData_hora().format(formatter)+" (Geral)"+": "+sessaoObservacao.getObservacao());
                        sessaoRepository.addObservacaoSemFase(sessaoObservacao.getObservacao(), sessaoObservacao.getData_hora(), id);
                    }
                }
                else
                {
                    SessaoObservacao sessaoObservacaoAntigo = sessao.getSessaoObservacaos();

                    if (sessaoObservacao.getSessaoFase() != null)
                    {
                        String concatenada = sessaoObservacaoAntigo.getObservacao()+"\n"+ sessaoObservacao.getData_hora().format(formatter)+" ("+sessaoObservacao.getSessaoFase().getExercicio().getNome()+")"+": "+sessaoObservacao.getObservacao();
                        sessaoObservacao.setObservacao(concatenada);
                        sessaoRepository.updateObservacao(sessaoObservacao.getObservacao(), sessaoObservacao.getData_hora(), sessaoObservacao.getSessaoFase().getId(), id);
                    }
                    else
                    {
                        String concatenada = sessaoObservacaoAntigo.getObservacao()+"\n"+sessaoObservacao.getData_hora().format(formatter)+" (Geral)"+": "+sessaoObservacao.getObservacao();
                        sessaoObservacao.setObservacao(concatenada);
                        sessaoRepository.updateObservacaoSemFase(sessaoObservacao.getObservacao(), sessaoObservacao.getData_hora(), id);
                    }

                }

                return sessaoObservacao;
            }
            return null;
        }catch (Exception e)
        {
            return null;
        }
    }

    public List<SessaoObservacao> getAllObservacoesByPacienteAndSessao(Long idSessao, Long idPaciente)
    {
        return sessaoRepository.getAllObservacoesByPacienteAndSessao(idSessao, idPaciente);
    }

    public boolean addGravacao(Long id, MultipartFile arquivoVideo)
    {
        try{

            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                //String nomePaciente = sessao.getPaciente().getNome().trim().replaceAll("[^a-zA-Z0-9]", "_");
                String nomePaciente = sessao.getPaciente().getNome().replaceAll(" ", "");
                //String pasta = "D:/Gravacoes/Paciente_"+nomePaciente+"/"+"Sessao"+id+"/"; // windows
                String pasta = "/home/matheus/Documents/PG2/Gravacoes/Paciente_"+nomePaciente+"/"+"Sessao"+id+"/"; // linux
                File diretorio = new File(pasta);
                if (!diretorio.exists())
                    diretorio.mkdirs();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
                String dataFormatada = LocalDateTime.now().format(formatter);
                String nomeArquivo = "sessao_" + dataFormatada + ".webm";
                Path caminho = Paths.get(pasta + nomeArquivo);
                Files.copy(arquivoVideo.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);

                SessaoGravacao gravacao = new SessaoGravacao();
                gravacao.setSessao(sessao);
                gravacao.setCaminho_arquivo(caminho.toString());
                gravacao.setData_hora(LocalDateTime.now());
                sessaoRepository.addGravacao(caminho.toString(), gravacao.getData_hora(), id);
                return true;

            }
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<SessaoGravacao> getAllGravacoesByPacienteAndSessao(Long idSessao, Long idPaciente)
    {
        return sessaoRepository.getAllGravacoesByPacienteAndSessao(idSessao, idPaciente);
    }

    public Resource playGravacao(Long id)
    {
        try {

            SessaoGravacao sessaoGravacao = sessaoRepository.getSessaoGravacaoById(id);
            if (sessaoGravacao != null)
            {
                Path caminhoVideo = Paths.get(sessaoGravacao.getCaminho_arquivo());
                Resource recursoVideo = new UrlResource(caminhoVideo.toUri());
                if (recursoVideo.exists() && recursoVideo.isReadable())
                {
                    return recursoVideo;
                }
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteGravacao(Long id)
    {
        try{
            SessaoGravacao sessaoGravacao = sessaoRepository.getSessaoGravacaoById(id);
            if (sessaoGravacao != null)
            {
                Path caminhoVideo = Paths.get(sessaoGravacao.getCaminho_arquivo());
                Files.delete(caminhoVideo);
                sessaoRepository.deleteSessaoGravacao(id);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public boolean deleteGravacaoAll(Long id)
    {
        try{
            SessaoGravacao sessaoGravacao = sessaoRepository.getSessaoGravacaoById(id);
            if (sessaoGravacao != null)
            {
                Path caminhoVideo = Paths.get(sessaoGravacao.getCaminho_arquivo());
                Files.delete(caminhoVideo);
                sessaoRepository.deleteSessaoGravacao(id);
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean enviarMensagem(String msg)
    {
        try {
            String comandoJson = String.format("{\"acao\": \"ENVIAR_MENSAGEM\", \"pacote_unity\": \"%s\", \"mensagem\": \"%s\" , \"sessao_id\": %d}", null, msg, null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean reiniciarSessao(Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                sessao.setStatus("APROVADA");
                sessao.setOrdemAtual(0);
                String lobby = "com.unoeste.lobby";
                String comandoJson = String.format("{\"acao\": \"REINICIAR\", \"pacote_unity\": \"%s\", \"repeticao\": \"%d\", \"mensagem\": \"%s\" , \"sessao_id\": %d}", lobby, 0, null, null);

                if (webSocketHandler.enviarComandoParaUnity(comandoJson))
                {
                    sessaoRepository.save(sessao);
                    return true;
                }
            }

            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean reiniciarExercicio()
    {
        try{
            String comandoJson = String.format("{\"acao\": \"REINICIAR_EXERCICIO\", \"pacote_unity\": \"%s\", \"mensagem\": \"%s\" , \"sessao_id\": %d}", null, null, null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                return true;
            }
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    @Transactional
    public void proximaFaseUnity(WebSocketSession session, Long sessaoId)
    {
        try {
            Sessao sessao = sessaoRepository.findById(sessaoId).orElse(null);
            if (sessao != null)
            {
                System.out.println(sessao.getOrdemAtual());
                System.out.println(sessao.getSessaoFases().size());
                if (sessao.getOrdemAtual() < sessao.getSessaoFases().size())
                {
                    sessao.setOrdemAtual(sessao.getOrdemAtual() + 1);
                    Exercicio exercicio = null;
                    Integer repeticao = null;
                    Float tempo = null;
                    boolean isRepeticao = true;
                    Long sessao_id = null;
                    for (int i = 0; i < sessao.getSessaoFases().size(); i++)
                    {
                        if (sessao.getSessaoFases().get(i).getOrdem() == sessao.getOrdemAtual())
                        {
                            exercicio = sessao.getSessaoFases().get(i).getExercicio();
                            repeticao = sessao.getSessaoFases().get(i).getRepeticao();
                            tempo = sessao.getSessaoFases().get(i).getTempo();
                            isRepeticao = sessao.getSessaoFases().get(i).getIs_repeticao();
                            sessao_id = sessao.getId();
                        }
                    }
                    Pacote pacote = new Pacote();
                    pacote.setAcao("PROXIMA_FASE_UNITY");
                    pacote.setSessao_id(sessao_id);
                    pacote.setIs_repeticao(isRepeticao);
                    pacote.setTempo(tempo);
                    pacote.setRepeticao(repeticao);
                    pacote.setPacote_unity(exercicio.getCodigo_nome());
                    String json = objectMapper.writeValueAsString(pacote);
                    session.sendMessage(new TextMessage(json));
                    sessaoRepository.save(sessao);
                }
                else
                {
                    Pacote pacote = new Pacote();
                    pacote.setAcao("FINALIZAR_SESSAO");
                    pacote.setPacote_unity("com.unoeste.lobby");
                    String json = objectMapper.writeValueAsString(pacote);
                    session.sendMessage(new TextMessage(json));
                    // finalizar depois

                }
            }

        }catch (Exception e){
        }

    }

    public SessaoObservacao getSessaoObservacaoById(Long id)
    {
        return sessaoRepository.getSessaoObservacaoById(id);
    }

    public List<Sessao> getAllByUsuarioIdAndStatus(Long id, String status)
    {
        return sessaoRepository.getAllUsuarioByIdAndStatus(id, status);
    }
}
