package com.examen.service;


import com.examen.model.Client;
import com.examen.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ImpClientService implements IClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ImpClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client creerClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> recupererClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client modifierClient(Long id, Client client) {


        Optional<Client> clientrechercher = clientRepository.findById(id);
        if (!clientrechercher.isPresent()) {
            System.out.println("Client non trouvé");
            return null;
        }

        Client clientmodifier = clientrechercher.get();
        clientmodifier.setNom(client.getNom());
        clientmodifier.setPrenom(client.getPrenom());
        clientmodifier.setEmail(client.getEmail());
        clientmodifier.setTelephone(client.getTelephone());
        return clientRepository.save(clientmodifier);
    }

        @Override
        public void supprimerClient (Long id){
            clientRepository.findById(id);
            if (!clientRepository.existsById(id)) {
                throw new RuntimeException("Client non trouvé");
            }
            clientRepository.deleteById(id);
        }
    }

