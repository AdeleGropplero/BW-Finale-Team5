package com.BuildWeek.Team5.Service;

import com.BuildWeek.Team5.Model.Cliente;
import com.BuildWeek.Team5.Payload.ClienteDTO;
import com.BuildWeek.Team5.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    //salva
    public Long salvaCliente(ClienteDTO clienteDTO){
        Cliente cliente = fromClienteDTOtoCliente(clienteDTO);
        clienteRepository.save(cliente);
        return cliente.getIdCliente();
    }






    //travaso
    public Cliente fromClienteDTOtoCliente(ClienteDTO clienteDTO){
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

    public ClienteDTO fromClienteToClienteDTO(Cliente cliente){
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
        return clienteDTO;
    }



}
