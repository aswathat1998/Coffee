package in.wizelab.coffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvCost;
    EditText etPrice;
    Button bAdd,bSub;

    int qty=0;
    float totalCost=0,price=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCost=(TextView)findViewById(R.id.tvTotal);
        bAdd=(Button) findViewById(R.id.bAdd);
        bSub=(Button) findViewById(R.id.bSub);
        etPrice =(EditText) findViewById(R.id.etPrice);

        etPrice.setText("7.8");
        price=Float.valueOf(etPrice.getText().toString());
        updateTotal();

        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0) {
                    price = Float.valueOf(s.toString());
                    updateTotal();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                updateTotal();
            }
        });
        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty>=1) {
                    qty--;
                    updateTotal();
                }else{
                    Toast.makeText(MainActivity.this,"Add more coffee :->",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    void updateTotal(){
        totalCost=qty*price;
        tvCost.setText("Quantity: "+qty+"\nTotal Cost: ₹" + String.valueOf(round(totalCost)));
    }
    double round(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

}
