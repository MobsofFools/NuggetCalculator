package nuggies;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbCountry;

    @FXML
    private Button btnCalculate;

    @FXML
    private TextField tfMoneySpent;

    @FXML
    private ComboBox<String> cbCurrency;

    @FXML
    private TextField tfTotalNuggies;

    @FXML
    private TextField tf40pc;

    @FXML
    private TextField tf20pc;

    @FXML
    private TextField tf10pc;

    @FXML
    private TextField tf6pc;

    @FXML
    private TextField tf4pc;

    @FXML
    private TextField tfTax;

    @FXML
    void setNuggies(ActionEvent event) {
        Nuggies();
        if (cbCountry.getValue().equals("USA")) {
            cbCurrency.setValue("USD");
        }
        else if(cbCountry.getValue().equals("Canada"))
        {
            cbCurrency.setValue("CAD");
        }
    }
    @FXML
    void setMod(ActionEvent event) {
        currencyMod();
    }

    @FXML
    void calculateNuggies(ActionEvent event) {
        calculateTotalNuggies();
        if (cbCountry.getValue().equals("Canada"))
        {
            tf40pc.setDisable(true);
            tf4pc.setDisable(true);
            tf40pc.setText("");
            tf4pc.setText("");
        }

    }

    @FXML
    void initialize() {
        assert cbCountry != null : "fx:id=\"cbCountry\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert btnCalculate != null : "fx:id=\"btnCalculate\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tfMoneySpent != null : "fx:id=\"tfMoneySpent\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert cbCurrency != null : "fx:id=\"cbCurrency\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tfTotalNuggies != null : "fx:id=\"tfTotalNuggies\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tf40pc != null : "fx:id=\"tf40pc\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tf20pc != null : "fx:id=\"tf20pc\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tf10pc != null : "fx:id=\"tf10pc\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tf6pc != null : "fx:id=\"tf6pc\" was not injected: check your FXML file 'nuggies.fxml'.";
        assert tf4pc != null : "fx:id=\"tf4pc\" was not injected: check your FXML file 'nuggies.fxml'.";

        //Initialize Combo Boxes
        ObservableList<String> countries = FXCollections.observableArrayList("USA","Canada");
        cbCountry.getItems().addAll(countries);
        ObservableList<String> currencies = FXCollections.observableArrayList("USD", "CAD");
        cbCurrency.getItems().addAll(currencies);

    }
    double pc40;
    double pc20;
    double pc10;
    double pc6;
    double pc4;

    //Prices of Nuggets in respective currencies
    public void Nuggies()
    {

       if (cbCountry.getValue().equals("Canada"))
       {
           pc20 = 10.89;
           pc10 = 7.39;
           pc6 = 4.99;
       }
       else
       {
           pc40 = 8.99;
           pc20 = 5;
           pc10 = 4.49;
           pc6 = 3.99;
           pc4 = 1.99;
       }
    }
    double mod;
    public void currencyMod()
    {

        if (cbCountry.getValue().equals("Canada") && cbCurrency.getValue().equals("CAD"))
        {
            mod = 1;
        }
        else if (cbCountry.getValue().equals("Canada") && cbCurrency.getValue().equals("USD"))
        {
            mod = 1.34;
        }
        else if(cbCountry.getValue().equals("USA") && cbCurrency.getValue().equals("USD"))
        {
            mod = 1;
        }
        else if(cbCountry.getValue().equals("USA") && cbCurrency.getValue().equals("CAD"))
        {
            mod = 0.75;
        }
    }
    private void calculateTotalNuggies()
    {
        double spent;
        int total;
        int num40;
        int num20;
        int num10;
        int num6;
        int num4;
        double tax;
        if (tfTax.getText().equals(""))
        {
            tax = 1;
        }
        else
            tax = Double.valueOf(tfTax.getText());

        if (tfMoneySpent.getText().equals(""))
        {
            spent = 0;
            num40 = 0;
            num20 = 0;
            num10 = 0;
            num6 = 0;
            num4 = 0;

        }
        else
        {
            spent = Double.parseDouble(tfMoneySpent.getText()) * mod;
            if(cbCountry.getValue().equals("Canada"))
            {
                num20 = (int)((spent)/(pc20*tax));
                num10 = (int)((spent-(num20*pc20*tax))/(pc10*tax));
                num6 = (int)((spent-(num20*pc20*tax)-(num10*pc10*tax))/(pc6*tax));
                total = num20*20 +num10*10 +num6*6;
                tf20pc.setText(String.valueOf(num20));
                tf10pc.setText(String.valueOf(num10));
                tf6pc.setText(String.valueOf(num6));
            }
            else{
                num40 = (int)(spent/(pc40*tax));
                num20 = (int)((spent-(num40*pc40*tax))/(pc20*tax));
                num10 = (int)((spent-(num40*pc40*tax)-(num20*pc20*tax))/(pc10*tax));
                num6 = (int)((spent-(num40*pc40*tax)-(num20*pc20*tax)-(num10*pc10*tax))/(pc6*tax));
                num4 = (int)((spent-(num40*pc40*tax)-(num20*pc20*tax)-(num10*pc10*tax)-(num6*pc6*tax))/(pc4*tax));
                total = num40*40+num20*20+num10*10+num6*6+num4*4;
                tf40pc.setText(String.valueOf(num40));
                tf20pc.setText(String.valueOf(num20));
                tf10pc.setText(String.valueOf(num10));
                tf6pc.setText(String.valueOf(num6));
                tf4pc.setText(String.valueOf(num4));

            }
            tfTotalNuggies.setText(String.valueOf(total));
        }
    }
}
