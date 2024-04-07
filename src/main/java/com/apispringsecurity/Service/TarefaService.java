package com.apispringsecurity.Service;

import com.apispringsecurity.Controller.dto.request.InserirTarefaRequest;
import com.apispringsecurity.Controller.dto.response.TarefaResponse;
import com.apispringsecurity.datasource.Entity.TarefaEntity;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import com.apispringsecurity.datasource.repository.TarefaRepository;
import com.apispringsecurity.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtDecoder jwtDecoder;

    public List<TarefaResponse> retornaTarefas(String token){

        Long idUsuario = Long.valueOf(
                //  decodifica o token, busca os campos do token, puxa o valor do campo "subject" do token e retorna o valor como String
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        List<TarefaEntity> tarefaEntities = tarefaRepository.findAllByUsuarioId(idUsuario);
        List<TarefaResponse> tarefaResponseList = new ArrayList<>();

        tarefaEntities.forEach( t-> tarefaResponseList.add(
                new TarefaResponse(t.getId(), t.getTitulo(), t.getDescricao(), t.getFinalizada())
        ));

        return tarefaResponseList;

    }

    public TarefaResponse salvaTarefa(InserirTarefaRequest incluiTarefaRequest, String token) {
        Long idUsuario = Long.valueOf(
                //  decodifica o token, busca os campos do token, puxa o valor do campo "sub" do token e retorna o valor como String
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow();

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setUsuario(usuario);
        tarefaEntity.setTitulo(incluiTarefaRequest.titulo());
        tarefaEntity.setDescricao(incluiTarefaRequest.descricao());
        tarefaEntity.setFinalizada(false);

        TarefaEntity tarefaSalva = tarefaRepository.save(tarefaEntity);
        return new
                TarefaResponse(tarefaSalva.getId(), tarefaSalva.getTitulo(), tarefaSalva.getDescricao(), tarefaSalva.getFinalizada());

    }
}
