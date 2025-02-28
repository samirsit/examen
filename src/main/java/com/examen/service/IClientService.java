package com.examen.service;


import com.examen.model.Client;

import java.util.Optional;

public interface IClientService {

    Client creerClient(Client client);
    Optional<Client> recupererClient (Long id);
    Client modifierClient (Long id, Client client);
    void supprimerClient(Long id);
}
