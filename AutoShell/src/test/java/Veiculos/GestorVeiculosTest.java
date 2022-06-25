package Veiculos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class GestorVeiculosTest {

    @Test
    public void deviaDarExceptionAoInserirMatriculaNull() throws IOException {
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        Assertions.assertThrows(RuntimeException.class, () -> {
            gestorVeiculos.insertVeiculos(null,"Teste","Teste","Teste","Teste","Teste","Teste");
        });
    }
    @Test
    public void deviaDarExceptionAoInserirMarcaNull() throws IOException {
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        Assertions.assertThrows(RuntimeException.class, () -> {
            gestorVeiculos.insertVeiculos("Teste",null,"Teste","Teste","Teste","Teste","Teste");
        });
    }
    @Test
    public void deviaDarExceptionAoInserirModeloNull() throws IOException {
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        Assertions.assertThrows(RuntimeException.class, () -> {
            gestorVeiculos.insertVeiculos("Teste","Teste",null,"Teste","Teste","Teste","Teste");
        });
    }
    @Test
    public void deviaDarExceptionAoInserirPrecoNull() throws IOException {
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        Assertions.assertThrows(RuntimeException.class, () -> {
            gestorVeiculos.insertVeiculos("Teste","Teste","Teste",null,"Teste","Teste","Teste");
        });
    }

    @Test
    public void deviaDarExceptionAoInserirMatriculaDuplicada() throws IOException {
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        Assertions.assertThrows(RuntimeException.class, () -> {
            gestorVeiculos.checkMatriculaDuplicada("Teste");
        });
    }

    @Test
    public void deviaCriarVeiculo() throws IOException {
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        gestorVeiculos.insertVeiculos("Teste123", "Teste", "Teste", "Teste", "Teste", "Teste", null);
        Assertions.assertEquals(1,gestorVeiculos.selectVeiculosMatricula("Teste123").length);
    }

    @Test
    public void deviaSelecionarTodosOsCamposVeiculos(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        DefaultTableModel model = null;
        //Assertions.assertEquals(8, .size);
    }

    @Test
    public void deviaSelecionarTodosONomeVeiculos(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }

    @Test
    public void deviaSelecionarTodosAMarcaVeiculos(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }

    @Test
    public void deviaSelecionarTodosOModeloVeiculos(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }

    @Test
    public void deviaEliminarUmVeiculo(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }
    @Test
    public void deviaEditarUmVeiculo(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }
    @Test
    public void deviaExportarTodosVeiculos(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }
    @Test
    public void deviaImportarTodosVeiculos(){
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
    }
}