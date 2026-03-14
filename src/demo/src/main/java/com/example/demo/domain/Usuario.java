package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario implements UserDetails {

  @Id
  @Column(name = "usuario_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long usuarioId;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido_paterno")
  private String apellidoPaterno;

  @Column(name = "apellido_materno")
  private String apellidoMaterno;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  @Column(name = "registrado_en", insertable = false)
  private LocalDateTime registradoEn;

  // Se deja presente porque se está impementando UserDetails
  // En caso de que la "Entidad" tuviera "email" y  no "username", se debería retornar "email" en este *getter*
  @Override
  public String getUsername() {
    return username;
  }

  // Se deja presente porque se está impementando UserDetails
  @Override
  public String getPassword() {
    return password;
  }

  // Se deja presente porque se está impementando UserDetails
  /**
   * Implementación de UserDetails para Spring Security.
   * En este caso, no se asignan roles ni permisos específicos,
   * por lo que se devuelve una lista vacía de autoridades.
   * Si en el futuro se desea agregar roles o permisos,
   * se puede modificar este método para incluirlos en el token JWT generado.
   */
  @Override
  public List<GrantedAuthority> getAuthorities() {
    return List.of();
  }

}
