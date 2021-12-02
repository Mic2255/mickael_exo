package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myfirstapp.Dao.ProductDao;
import com.example.myfirstapp.entities.Product;
import com.example.myfirstapp.vebServices.ProductWebService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText designationEditText ;
    private TextInputEditText descriptionEditText ;
    private TextInputEditText prixEditText ;
    private TextInputEditText quantiteStockEditText ;
    private TextInputEditText alertQuantityEditText ;
    private Button ajouterButton;
    boolean good;
    private ProductDao productDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productDao = new ProductDao(this);

        designationEditText = findViewById(R.id.name);
        descriptionEditText = findViewById(R.id.desc);
        prixEditText = findViewById(R.id.price);
        quantiteStockEditText = findViewById(R.id.qt_stock);
        alertQuantityEditText = findViewById(R.id.alert_qt);
        ajouterButton = findViewById(R.id.mybtn);

    }



    public void saveProduct(View view){
        productDao = new ProductDao(this);
        good = true ;
        checkExistingInput(designationEditText);
        checkExistingInput(descriptionEditText);
        checkExistingInput(prixEditText);
        checkExistingInput(quantiteStockEditText);
        checkExistingInput(alertQuantityEditText);


            if (designationEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty() || prixEditText.getText().toString().isEmpty() ||quantiteStockEditText.getText().toString().isEmpty() || alertQuantityEditText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Vous devez remplir tout les champs", Toast.LENGTH_SHORT).show();
            }else{
                Product product = new Product();
                product.name = designationEditText.getText().toString();
                product.description = descriptionEditText.getText().toString();
                product.price = Double.parseDouble(prixEditText.getText().toString());
                product.quantityInStock = Double.parseDouble(quantiteStockEditText.getText().toString());
                product.alertQuantity = Double.parseDouble(alertQuantityEditText.getText().toString());

                new Thread(
                        ()->{
                            ProductWebService productWebService = new ProductWebService();
                            Product save = productWebService.createProduct(product);
                            System.out.println(save);
                            System.out.println("save :: " + save);
                            runOnUiThread(()->{
                                if (save != null){
                                    productDao.insert(product);
                                }
                            });
                        }
                ).start();

                Intent intent =  getIntent();
                intent.putExtra("MY_P" , product);
                setResult(RESULT_OK, intent);
                finish();

            }

        }


    public void checkExistingInput(TextInputEditText inp){

        if (Objects.requireNonNull(inp.getText()).toString().isEmpty()){
            good = false ;
        }
    }

    @Override
    public void onClick(View view) {
        saveProduct(view);
    }

}