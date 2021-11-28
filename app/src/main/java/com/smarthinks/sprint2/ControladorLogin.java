package com.smarthinks.sprint2;

public class ControladorLogin implements LoginInterface.controlador{

    private final LoginInterface.View view;

    public ControladorLogin(LoginInterface.View view){
        this.view = view;
    }

    @Override
    public boolean validarLogin(String editText, String indicador){
        return true;
    }

}
