package com.example.mvvm3.mv;


import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mvvm3.model.Lcee;
import com.example.mvvm3.model.User;
import com.example.mvvm3.repository.UserRepository;

/**
 * @author Jian.cui
 * @date 2019/11/3 18:59
 */
public class UserViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<String> ldUsername;
    private LiveData<Lcee<User>> ldUser;

    public LiveData<Lcee<User>> getUser() {
        if (null == ldUser) {
            ldUsername = new MutableLiveData<>();
            ldUser = Transformations.switchMap(ldUsername, new Function<String, LiveData<Lcee<User>>>() {
                @Override
                public LiveData<Lcee<User>> apply(String username) {
                    return userRepository.getUser(username);
                }
            });
        }
        return ldUser;
    }

    public void reload(String username) {
        ldUsername.setValue(username);
    }

}
