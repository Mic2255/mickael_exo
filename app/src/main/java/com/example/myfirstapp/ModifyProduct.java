package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class ModifyProduct extends AppCompatActivity {

    private TextInputEditText designationEditText ;
    private TextInputEditText descriptionEditText ;
    private TextInputEditText prixEditText ;
    private TextInputEditText quantiteStockEditText ;
    private TextInputEditText alertQuantityEditText ;
    private Button enregistrerButton;
    boolean good;
    private ProductDao productDao ;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product);
        productDao = new ProductDao(this);

        designationEditText = findViewById(R.id.name_r);
        descriptionEditText = findViewById(R.id.desc_r);
        prixEditText = findViewById(R.id.price_r);
        quantiteStockEditText = findViewById(R.id.qt_stock_r);
        alertQuantityEditText = findViewById(R.id.alert_qt_r);
        enregistrerButton = findViewById(R.id.mybtnE);

        Product product_r = (Product) getIntent().getSerializableExtra("PROD_R");
        designationEditText.setText(product_r.name);
        descriptionEditText.setText(product_r.description);
        prixEditText.setText(Double.toString(product_r.price));
        quantiteStockEditText.setText(Double.toString(product_r.quantityInStock));
        alertQuantityEditText.setText(Double.toString(product_r.alertQuantity));

        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (designationEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty() || prixEditText.getText().toString().isEmpty() ||quantiteStockEditText.getText().toString().isEmpty() || alertQuantityEditText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vous devez remplir tous les champs", Toast.LENGTH_SHORT).show();

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
                                Product save = productWebService.updateProduct(product);
                                System.out.println(save);
                                System.out.println("save :: " + save);
                                runOnUiThread(()->{
                                    if (save != null){
                                        productDao.update(product_r.id, product);
                                    }
                                });
                            }
                    ).start();

                    Intent intent = new Intent(ModifyProduct.this , ProductList.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }




    public void checkExistingInput(TextInputEditText inp){

        if (Objects.requireNonNull(inp.getText()).toString().isEmpty()){
            good = false ;
        }
    }
}