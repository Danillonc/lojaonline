package br.com.lojaonline.service;

import br.com.lojaonline.commons.enums.MessagesEnum;
import br.com.lojaonline.commons.mapper.ProdutoMapper;
import br.com.lojaonline.commons.response.Response;
import br.com.lojaonline.domain.Produto;
import br.com.lojaonline.dto.ProdutoDTO;
import br.com.lojaonline.exception.ResourceAlreadyExistsException;
import br.com.lojaonline.exception.ResourceNotFoundException;
import br.com.lojaonline.repository.ProdutosRepository;
import br.com.lojaonline.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class ProdutoServiceImplTests {

    @InjectMocks
    private ProdutoServiceImpl service;

    @Mock
    private ProdutosRepository repository;

    @Test
    public void cadastrarProdutoSucesso() {
        Response<Void> expectedResponse = new Response<>();
        expectedResponse.addMessage(MessagesEnum.SUCESSO);

        Mockito.when(this.repository.save(Mockito.any(Produto.class))).thenReturn(mockEntity());
        Response<Void> actualResponse = this.service.cadastrarProduto(ProdutoMapper.toEntity(mockDto()));

        Assertions.assertEquals(expectedResponse.getMessages().stream().findFirst().get(),
                actualResponse.getMessages().stream().findFirst().get());
        Assertions.assertNotNull(actualResponse);
    }

    @Test
    public void cadastrarProdutoExistenteErro() {

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.of(mockEntity()));

        ResourceAlreadyExistsException ex = Assertions.assertThrows(ResourceAlreadyExistsException.class,
                () -> this.service.cadastrarProduto(ProdutoMapper.toEntity(mockDto())));

        Assertions.assertEquals(MessagesEnum.PRODUTO_EXISTENTE.getMessage(), ex.getMessage());
    }

    @Test
    public void consultarProdutoUnicoSucesso() {
        Response<List<ProdutoDTO>> expectedResponse = new Response<>();
        expectedResponse.addMessage(MessagesEnum.SUCESSO);

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.of(mockEntity()));
        Response<List<ProdutoDTO>> actualResponse = this.service.consultarProdutos("123");

        Assertions.assertAll(
                () -> Assertions.assertTrue(actualResponse.getData().size() >= 1),
                () -> Assertions.assertEquals(expectedResponse.getMessages().stream().findFirst().get(),
                        actualResponse.getMessages().stream().findFirst().get())
        );

    }

    @Test
    public void consultarProdutoUnicoNaoEncontrado() {

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.empty());

        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> this.service.consultarProdutos("123"));

        Assertions.assertEquals(MessagesEnum.PRODUTO_NAO_ENCONTRADO.getMessage(), ex.getMessage());
    }

    @Test
    public void atualizarProdutoSucesso() {
        Response<Void> expectedResponse = new Response<>();
        expectedResponse.addMessage(MessagesEnum.SUCESSO);

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.of(mockEntity()));
        Response<Void> actualResponse = this.service.atualizarProduto(mockEntity(), "123");

        Assertions.assertEquals(expectedResponse.getMessages().stream().findFirst().get(),
                actualResponse.getMessages().stream().findFirst().get());
        Assertions.assertNotNull(actualResponse);
    }

    @Test
    public void atualizarProdutoErro() {

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.empty());

        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> this.service.atualizarProduto(mockEntity(), "123"));

        Assertions.assertEquals(MessagesEnum.PRODUTO_NAO_ENCONTRADO.getMessage(), ex.getMessage());
    }

    @Test
    public void deletarProdutoSucesso(){
        Response<Void> expectedResponse = new Response<>();
        expectedResponse.addMessage(MessagesEnum.SUCESSO);

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.of(mockEntity()));
        Response<Void> actualResponse = this.service.deletarProduto( "123");

        Assertions.assertEquals(expectedResponse.getMessages().stream().findFirst().get(),
                actualResponse.getMessages().stream().findFirst().get());
        Assertions.assertNotNull(actualResponse);
    }

    @Test
    public void deletarProdutoErro(){

        Mockito.when(this.repository.findByCodigoProduto(Mockito.anyString())).thenReturn(Optional.empty());

        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> this.service.deletarProduto( "123"));

        Assertions.assertEquals(MessagesEnum.PRODUTO_NAO_ENCONTRADO.getMessage(), ex.getMessage());
    }

    private Produto mockEntity() {
        return Produto.builder().codigoProduto("123L").descricao("Volante").estoque(20).preco(BigDecimal.valueOf(120.00))
                .cor("Preto").serie("12345").modelo("2v").fabricante("GM").build();
    }

    private ProdutoDTO mockDto() {
        return ProdutoDTO.builder().codigoProduto("9991L").descricao("serrote")
                .estoque(300).preco(BigDecimal.valueOf(45.00)).build();
    }
}
