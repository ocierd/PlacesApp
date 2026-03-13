package com.example.demo.services.interfaces;

import com.example.demo.domain.Sms;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
public interface SmsService {

    void enviarSMS(String destino, String mensaje);

    Sms procesarEnvioTokenSMS(Sms sms)
    throws ValidacionException;

    void actualizarSms(Integer smsId)
    throws ValidacionException,NoEncontradoException;
}
