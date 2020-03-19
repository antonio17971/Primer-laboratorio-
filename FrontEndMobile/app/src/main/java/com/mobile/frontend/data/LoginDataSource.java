package com.mobile.frontend.data;

import com.mobile.frontend.data.model.UserModel;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<UserModel> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
//            UserModel fakeUser =
//                    new UserModel(
//                            java.util.UUID.randomUUID().toString(),
//                            "Jane Doe");
//            return new Result.Success<UserModel>(fakeUser);
            return  new Result.Success<UserModel>(new UserModel());
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
