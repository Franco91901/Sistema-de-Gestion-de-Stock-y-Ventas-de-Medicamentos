package com.projecto.soap.services;

import com.example.anuncio.ObtenerAnunciosResponse;
import com.projecto.soap.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService1 {

    @Autowired
    private AnuncioRepository repo;

    public ObtenerAnunciosResponse findAll() {
        ObtenerAnunciosResponse response=new ObtenerAnunciosResponse();
        response.setLista(repo.listProductos());
        return response;
    }


}
