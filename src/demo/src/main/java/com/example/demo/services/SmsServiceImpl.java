package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.domain.Sms;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.repository.SmsRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.config.from_number}")
    private String FROM_NUMBER;

    @Value("${places_app.sms.expiration}")
    private Integer EXPIRATION_TIME;

    private final SmsRepository smsRepository;
    private final UsuarioRepository usuarioRepository;

    public SmsServiceImpl(SmsRepository smsRepository, UsuarioRepository usuarioRepository) {
        this.smsRepository=smsRepository;
        this.usuarioRepository=usuarioRepository;

    }

    

    @Override
     @Transactional(rollbackFor = Exception.class)
    public void enviarSMS(String destino, String mensaje) {

        PhoneNumber to = new PhoneNumber(destino);
        PhoneNumber from = new PhoneNumber(FROM_NUMBER);

        MessageCreator creator = Message.creator(
                to, // Destino
                from, // Origen
                mensaje) // Mensaje
        ;
        Message message = creator.create();

        System.out.println("SID del mensaje: " + message.getSid());
    }




    @Override
    @Transactional(rollbackFor = Exception.class)
    public Sms procesarEnvioTokenSMS(Sms sms) {
     Long codigo= ThreadLocalRandom.current().nextLong(10000, 80000 + 1);
       Sms creado= smsRepository.save(sms);
       creado.setFechaExpiracion(LocalDateTime.now().plusSeconds(EXPIRATION_TIME));
       creado.setCodigo(codigo);
       Integer smsId=creado.getSmsId();
        if (smsId != null) {
            return smsRepository.findById(smsId)
                    .orElse(null);
        }
        return creado;
    }




    @Override

    public void actualizarSms(Integer smsId)
     throws ValidacionException,NoEncontradoException {

      Sms sms = smsRepository.findById(smsId)
                .orElse(null);
        if (sms != null) {
                 if (sms.getFechaConfirmacion()!=null) {
            throw new ValidacionException("El codigo ya fue validado");
        }


            if (sms.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            throw new ValidacionException("El codigo ya expiro");
        }
        

            sms.setFechaConfirmacion(LocalDateTime.now());
            smsRepository.save(sms);
            Long usuarioID=sms.getUsuarioId();

            Usuario usuario=usuarioRepository.findById(usuarioID)
                            .orElse(null);
            usuario.setTelefonoVerificado(true);
            usuarioRepository.save(usuario);
        }
        else
        {
            throw new NoEncontradoException();
        }
    }

}
