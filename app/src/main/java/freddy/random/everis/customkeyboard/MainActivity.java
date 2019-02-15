package freddy.random.everis.customkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements viewInterface {

    private List<Integer> array = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private MyKeyboard keyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        keyboard = findViewById(R.id.keyboard);
        keyboard.init(shuffleNumbers());
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

    }

    private List<Integer> shuffleNumbers() {
        List<Integer> temp = array;
        Collections.shuffle(temp);
        return temp;
    }

    @Override
    public void shuffle() {
        keyboard.init(shuffleNumbers());
    }
}
