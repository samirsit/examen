package com.examen.Controller;

import com.examen.model.Client;
import com.examen.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/recuperer-client/{id}")
    public Optional<Client> recupererClient(@PathVariable Long id) {
        return clientService.recupererClient(id);
    }

    @PostMapping("/creer-client")
    public Client creerClient(@RequestBody Client client) {
        return clientService.creerClient(client);
    }

    @PutMapping("/modifier-client/{id}")
    public Client modifierClient(@PathVariable Long id,
                                                 @RequestBody Client client) {
        return clientService.modifierClient(id, client);
    }

    @DeleteMapping("/supprimer-client/{id}")
    public void supprimerClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
    }
}
