package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int coffeePrice = 5000;
    int whippedCreamPrice = 1000;
    int chocolatePrice = 2000;
    int price = 0;
    int quantity=0;
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox choco = (CheckBox) findViewById(R.id.choco_checkbox);
        boolean hasChoco = choco.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_editText);
        String name = nameEditText.getText().toString();

        calculatePrice(hasWhippedCream,hasChoco);

        String priceMessage = createOrderSummary(name,price,hasWhippedCream,hasChoco);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " +name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void calculatePrice(boolean whippedCream,boolean Choco){
        if(whippedCream==false && Choco==false){
            price = coffeePrice * quantity;
        }
        else if(whippedCream==true){
            price = ( coffeePrice + whippedCreamPrice ) * quantity;
        }
        else if(Choco==true){
            price = ( coffeePrice + chocolatePrice ) * quantity;
        }
        if(whippedCream==true && Choco==true){
            price = ( coffeePrice + whippedCreamPrice + chocolatePrice ) * quantity;
        }
    }

    private String createOrderSummary(String name,int price, boolean whippedCream,boolean choco){
        String whipp="No";
        String choc="No";
        if (whippedCream==true){
            whipp = "Yes";
        }
        else if(whippedCream==false){
            whipp = "No";
        }
        if (choco==true){
            choc = "Yes";
        }
        else if(choco==false){
            choc = "No";
        }
        String priceMessage =
                "Name : "+name+
                "\nAdd Whipped Cream ? "+whipp+
                "\nAdd Chocolate ? "+choc+
                "\nQuantity : " + quantity +"\nTotal : Rp "+price+"\nThank You!";
        return priceMessage;
    }

    public void increment(View view){
        quantity++;
        display(quantity);
    }
    public void  decrement(View view){
        if(quantity>0) {
            quantity--;
        }
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


//    method display price yang lama
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText("Total = "+NumberFormat.getCurrencyInstance().format(number)+"\nThank You!");
//    }
//        private void displayMessage(String priceMessage){
//            TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
//            quantityTextView.setText(priceMessage);
//        }

}
