package com.example.mvvm3.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvm3.databinding.AActivityUserBinding;
import com.example.mvvm3.repository.local.DBHelper;
import com.example.mvvm3.R;
import com.example.mvvm3.model.Lcee;
import com.example.mvvm3.model.User;
import com.example.mvvm3.mv.UserViewModel;
import com.example.mvvm3.repository.UserRepository;

/**
 * @author Jian.cui
 * @date 2019/11/3 18:54
 */
public class UserActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private TextView tvId;
    private TextView tvName;
    //private Button button;
    private EditText etUsername;

    private View vContent;
    private View vError;
    private View vLoading;
    private View vEmpty;

    private AActivityUserBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_user);
        initView();
        initData();
    }

    private void initView() {
        tvId = (TextView) findViewById(R.id.tv_id);
        tvName = (TextView) findViewById(R.id.tv_name);
        //button = (Button) findViewById(R.id.button);
        etUsername = (EditText) findViewById(R.id.et_username);

        vContent = findViewById(R.id.v_content);
        vError = findViewById(R.id.v_error);
        vLoading = findViewById(R.id.v_loading);
        vEmpty = findViewById(R.id.v_empty);

        bind = DataBindingUtil.setContentView(this, R.layout.a_activity_user);
        bind.setUser(new User());

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String anotherName = "John Doe";
//                userViewModel.reload(anotherName);
//            }
//        });
    }

    private void initData() {
        DBHelper.getInstance().init(this);
        UserRepository.getInstance().init(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<Lcee<User>>() {
            @Override
            public void onChanged(@Nullable Lcee<User> user) {
                updateView(user);
            }
        });

        reload();
    }

    private void updateView(Lcee<User> lcee) {
        switch (lcee.status) {
            case Content: {
                showContent();
                bind.setUser(lcee.data);
                break;
            }
            case Empty: {
                showEmpty();
                break;
            }
            case Error: {
                showError();
                break;
            }
            case Loading: {
                showLoading();
                break;
            }
        }
    }

    private String getUsername() {
        return etUsername.getText().toString();
    }

    private void reload() {
        // reload
        userViewModel.reload(getUsername());
    }

    private void initEvent() {
        View.OnClickListener reloadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                reload();
            }
        };
        vError.setOnClickListener(reloadClickListener);
        vEmpty.setOnClickListener(reloadClickListener);

        findViewById(R.id.btn_search).setOnClickListener(reloadClickListener);

        etUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideKeyboard();
                    reload();
                    return true;
                }
                return false;
            }
        });
    }

    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(UserActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void showContent() {
        vContent.setVisibility(View.VISIBLE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showEmpty() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.VISIBLE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showError() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.VISIBLE);
        vLoading.setVisibility(View.GONE);
    }

    private void showLoading() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.VISIBLE);
    }


}
