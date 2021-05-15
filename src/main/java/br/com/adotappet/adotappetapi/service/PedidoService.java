package br.com.adotappet.adotappetapi.service;

import br.com.adotappet.adotappetapi.dto.PedidoDTO;
import br.com.adotappet.adotappetapi.dto.PetDTO;
import br.com.adotappet.adotappetapi.dto.UsuarioDTO;
import br.com.adotappet.adotappetapi.entity.Pet;
import br.com.adotappet.adotappetapi.exception.BusinessException;
import br.com.adotappet.adotappetapi.entity.Pedido;
import br.com.adotappet.adotappetapi.entity.Usuario;
import br.com.adotappet.adotappetapi.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
    }

    public PedidoDTO iniciaPedido(UsuarioDTO usuarioDTO, PetDTO petDTO) {
        Usuario usuario = toUsuarioEntity(usuarioDTO);
        Pet pet = toPetEntity(petDTO);
        Pedido pedido = Pedido.builder()
                            .usuario(usuario)
                            .pet(pet)
                            .status(Pedido.Status.ABERTO)
                            .build();

        return toPedidoDTO(pedidoRepository.save(pedido));
    }

    public PedidoDTO aprovaPedido(Long id, boolean aceito) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        if (!pedido.getStatus().equals(Pedido.Status.ABERTO)) {
            throw new BusinessException("Pedido não está em aberto");
        }
        pedido.setStatus(aceito ? Pedido.Status.ACEITO : Pedido.Status.REJEITADO);
        pedido.getPet().setDisponivel(!aceito);
        return toPedidoDTO(pedidoRepository.save(pedido));
    }

    public PedidoDTO finalizaPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        if (pedido.getStatus().equals(Pedido.Status.ABERTO)) {
            throw new BusinessException("Pedido está em aberto");
        }
        if (pedido.getStatus().equals(Pedido.Status.REJEITADO)) {
            throw new BusinessException("Pedido está rejeitado");
        }
        pedido.setStatus(Pedido.Status.FINALIZADO);
        return toPedidoDTO(pedidoRepository.save(pedido));
    }

    public PedidoDTO cancelaPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        if (pedido.getStatus().equals(Pedido.Status.FINALIZADO)) {
            throw new BusinessException("Pedido já foi finalizado");
        }
        if (pedido.getStatus().equals(Pedido.Status.REJEITADO)) {
            throw new BusinessException("Pedido está rejeitado");
        }
        pedido.setStatus(Pedido.Status.CANCELADO);
        return toPedidoDTO(pedidoRepository.save(pedido));
    }

    private PedidoDTO toPedidoDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    private Usuario toUsuarioEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    private Pet toPetEntity(PetDTO petDTO) {
        return modelMapper.map(petDTO, Pet.class);
    }

}
