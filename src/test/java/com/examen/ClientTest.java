package com.examen;

import com.examen.repository.ClientRepository;
import com.examen.service.ImpClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.examen.model.Client;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;  // Mock du repository

    @InjectMocks
    private ImpClientService impClientService;  // Injection du mock dans le service

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(Long.valueOf("1"));
        client.setNom("samir");
        client.setPrenom("sitahar");
        client.setEmail("samir.sitahar@gmail.com");
        client.setTelephone("0758804579");
    }

    @Test
    void CreerClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = impClientService.creerClient(client);

        assertNotNull(savedClient);
        assertEquals("samir.sitahar@gmail.com", savedClient.getEmail());  // Correction de l'email
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void ModifierClient() {
        Client nouveauClient = new Client();
        nouveauClient.setNom("Meheni");
        nouveauClient.setPrenom("Yazid");
        nouveauClient.setEmail("samir.sitahar1234@gmail.com");
        nouveauClient.setTelephone("0415422");

        when(clientRepository.findById(Long.valueOf("1"))).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(nouveauClient);

        Client updatedClient = impClientService.modifierClient(Long.valueOf("1"), nouveauClient);

        assertNotNull(updatedClient);
        assertEquals("Meheni", updatedClient.getNom());
        assertEquals("Yazid", updatedClient.getPrenom());
        assertEquals("samir.sitahar1234@gmail.com", updatedClient.getEmail());
        verify(clientRepository, times(1)).findById(Long.valueOf("1"));  // Assurez-vous d'utiliser l'ID du client comme String
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    // ✅ TEST : Supprimer un client existant
    @Test
    void SupprimerClient() {
        when(clientRepository.existsById(Long.valueOf("1"))).thenReturn(true);

        impClientService.supprimerClient(Long.valueOf("1"));

        verify(clientRepository, times(1)).deleteById(Long.valueOf("1"));
    }

    @Test
    public void RecupererClient() {
        // Given
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        // When
        Optional<Client> retrievedClient = impClientService.recupererClient(client.getId());

        // Then
        assertTrue(retrievedClient.isPresent());
        assertEquals(client.getNom(), retrievedClient.get().getNom());
        verify(clientRepository, times(1)).findById(client.getId());
    }

}
