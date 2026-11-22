package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Configuracao;
import com.unoeste.fallpreventionbe.repositories.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public boolean delete(Long id) {
        try{
            Configuracao configuracao = configuracaoRepository.findById(id).orElse(null);
            if (configuracao != null)
            {
                configuracaoRepository.delete(configuracao);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Configuracao getById(Long id)
    {
        return configuracaoRepository.findById(id).orElse(null);
    }

    public List<Configuracao> getAll() {
        return configuracaoRepository.findAll();
    }

    public Configuracao save(Configuracao configuracao)
    {
        try{
            return configuracaoRepository.save(configuracao);

        }catch (Exception e){
            e.printStackTrace();
            return null ;
        }
    }

    public String getLocalIPv4() {
        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (ni.isUp() && !ni.isLoopback() && !ni.isVirtual())
                {
                    for (InetAddress addr : Collections.list(ni.getInetAddresses())) {
                        if (addr instanceof Inet4Address && addr.isSiteLocalAddress()) {
                            return addr.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Configuracao AtualizarIPv4Local()
    {
        String iPv4Local = getLocalIPv4();
        Configuracao configuracao = null;
        if (!iPv4Local.isEmpty())
        {
            List<Configuracao> configuracoes = configuracaoRepository.findAll();

            if (configuracoes.isEmpty()) // nao ha nenhum registro
            {
                configuracao = new Configuracao(iPv4Local, false);
                configuracaoRepository.save(configuracao);
            }
            else
            {
                configuracao = configuracoes.get(0);
                if (!configuracao.getFlag()) // se for falsa, significa que ele não alterou manualmente.
                {
                    configuracao = configuracoes.get(0);
                    configuracao.setIp(iPv4Local);
                    configuracaoRepository.save(configuracao);
                }
            }
        }
        return configuracao;
    }

}
