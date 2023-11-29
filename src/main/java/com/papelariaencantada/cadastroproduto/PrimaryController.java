package com.papelariaencantada.cadastroproduto;

import java.io.IOException;
import java.math.BigDecimal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_sair;

    @FXML
    private Button btn_salvar;

    @FXML
    private ImageView imagem;

    @FXML
    private Label lbl_categoria;

    @FXML
    private Label lbl_compra;

    @FXML
    private Label lbl_fornecedor;

    @FXML
    private Label lbl_idproduto;

    @FXML
    private Label lbl_marca;

    @FXML
    private Label lbl_nome;

    @FXML
    private Label lbl_qntd;

    @FXML
    private Label lbl_ultcompra;

    @FXML
    private Label lbl_valores;

    @FXML
    private Label lbl_venda;

    @FXML
    private Label lbl_mensagem;


    @FXML
    private AnchorPane telaprincipal;

    @FXML
    private TextField txt_categoria;

    @FXML
    private TextField txt_compra;

    @FXML
    private TextField txt_fornecedor;

    @FXML
    private TextField txt_idproduto;

    @FXML
    private TextField txt_marca;

    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_qntd;

    @FXML
    private TextField txt_ultcompra;

    @FXML
    private TextField txt_venda;

    @FXML
    void editar(ActionEvent event) {
        try {
            // Carrega o FXML da nova tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CaminhoParaSuaNovaTela.fxml"));
            Parent root = loader.load();

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Cria um novo Stage (janela)
            Stage stage = new Stage();
            stage.setTitle("Título da Nova Tela");
            stage.setScene(scene);

            // Mostra a nova tela
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Ou trate a exceção conforme necessário
        }

    }

    @FXML
    void sair(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja sair?");

        // Personaliza os botões do alerta
        ButtonType buttonTypeSim = new ButtonType("Sim");
        ButtonType buttonTypeNao = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

        // Obtém a resposta do usuário
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeSim) {
                fecharJanela();
            }
        });
    }

    private void fecharJanela() {
        Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
    }
    @FXML
    void initialize() {
        // Adiciona um filtro para permitir apenas números nos campos numéricos
        adicionarFiltroNumerico(txt_compra);
        adicionarFiltroNumerico(txt_venda);
        adicionarFiltroNumerico(txt_qntd);
    }

    private void adicionarFiltroNumerico(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                campo.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void salvar(ActionEvent event) {
        System.out.println(txt_categoria.getText());

        //Chamar o DAO do produto aqui.

        if (camposValidos()) {
            mostrarAlerta("Produto Cadastrado", "O Produto foi cadastrado com sucesso.", Alert.AlertType.INFORMATION);
            limparCampos();
        } else {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente.", Alert.AlertType.ERROR);
        }

        
    }

    private boolean camposValidos() {
        try {
            BigDecimal compra = new BigDecimal(txt_compra.getText());
            BigDecimal venda = new BigDecimal(txt_venda.getText());
            int quantidade = Integer.parseInt(txt_qntd.getText());

            return !txt_nome.getText().isEmpty() &&
                    !txt_ultcompra.getText().isEmpty() &&
                    compra.compareTo(BigDecimal.ZERO) >= 0 &&
                    venda.compareTo(BigDecimal.ZERO) >= 0 &&
                    quantidade > 0;
        } catch (NumberFormatException e) {
            return false; // Se algum campo numérico não for um número válido
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        txt_nome.clear();
        txt_ultcompra.clear();
        txt_compra.clear();
        txt_venda.clear();
        txt_qntd.clear();
        txt_categoria.clear();
        txt_marca.clear();
        txt_fornecedor.clear();
        txt_idproduto.clear();
    }
}

    

