package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Exception.ClienteNotFound;
import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Payload.ClienteDTO;
import com.BuildWeek.Team5.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public Cliente trovaCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFound("Cliente non trovato"));
        return cliente;
    }

    //salva
    public Long salvaCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente.getIdCliente();
    }

    //Ordinamento liste clienti --------------------------------------------------

    public List<ClienteDTO> clientiAZ() {
        List<Cliente> clienti = clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nomeContatto"));
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    public List<ClienteDTO> clientiPerFatturato() {
        List<Cliente> clienti = clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "fatturatoAnnuale"));
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    public List<ClienteDTO> clientiPerDataInserimento() {
        List<Cliente> clienti = clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "dataInserimento"));
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    public List<ClienteDTO> clientiPerDataUltimoContatto() {
        List<Cliente> clienti = clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "dataUltimoContatto"));
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    public List<ClienteDTO> clientiPerProvinciaSedeLegale() {
        List<Cliente> clienti = clienteRepository.findByProvinciaSedeLegale();
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }


    //----------------------------------------------------------------------------------------------------


    public void leggiArrayClienti(ArrayList<ClienteDTO> clientiDTO) {

        for (ClienteDTO singoloCliente : clientiDTO) {
            Cliente nuovoCliente = fromClienteDTOtoCliente(singoloCliente);
            salvaCliente(nuovoCliente);
        }
    }

    //filtra per fatturato annuale
    public List<ClienteDTO> getByFatturatoAnnuale(double fatturatoAnnuale) {
        List<Cliente> clienti = clienteRepository.findByFatturatoAnnualeGreaterThan(fatturatoAnnuale);
        if (clienti.isEmpty()) {
            throw new ClienteNotFound("Nessun cliente ha questo fatturato annuale");
        }
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    //filtra per data di inserimento
    public List<ClienteDTO> getByDataInserimento(LocalDate dataInserimento) {
        List<Cliente> clienti= clienteRepository.findByDataInserimentoBefore(dataInserimento);
        if (clienti.isEmpty()) {
            throw new ClienteNotFound("Nessun cliente inserito prima di questa data!");
        }
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    public List<ClienteDTO> getByDataUltimoContatto(LocalDate dataUltimoContatto) {
        List<Cliente> clienti = clienteRepository.findByDataUltimoContattoBefore(dataUltimoContatto);
        if (clienti.isEmpty()) {
            throw new ClienteNotFound("Nessun cliente ha avuto l'ultimo contatto prima di questa data!");
        }
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    public List<ClienteDTO> getByNomeContatto(String nomeContatto) {
        List<Cliente> clienti = clienteRepository.findByNomeContattoContaining(nomeContatto);
        if (clienti.isEmpty()) {
            throw new ClienteNotFound("Nessun cliente ha questa stringa nel nome!");
        }
        List<ClienteDTO> clientiDTO = clienti.stream().map(cliente -> fromClienteToClienteDTO(cliente)).collect(Collectors.toList());
        return clientiDTO;
    }

    //travaso
    public Cliente fromClienteDTOtoCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setPartitaIva(clienteDTO.getPartitaIva());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDataInserimento(clienteDTO.getDataInserimento());
        cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
        cliente.setPec(clienteDTO.getPec());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEmailContatto(clienteDTO.getEmailContatto());
        cliente.setNomeContatto(clienteDTO.getNomeContatto());
        cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
        cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());
        cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
        cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
        return cliente;
    }

    public ClienteDTO fromClienteToClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setPartitaIva(cliente.getPartitaIva());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setDataInserimento(cliente.getDataInserimento());
        clienteDTO.setDataUltimoContatto(cliente.getDataUltimoContatto());
        clienteDTO.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
        clienteDTO.setPec(cliente.getPec());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setEmailContatto(cliente.getEmailContatto());
        clienteDTO.setNomeContatto(cliente.getNomeContatto());
        clienteDTO.setCognomeContatto(cliente.getCognomeContatto());
        clienteDTO.setTelefonoContatto(cliente.getTelefonoContatto());
        clienteDTO.setLogoAziendale(cliente.getLogoAziendale());
        clienteDTO.setRagioneSociale(cliente.getRagioneSociale());
        return clienteDTO;
    }


}
