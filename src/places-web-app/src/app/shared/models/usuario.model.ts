export interface UsuarioRegistroDto {

      username: string;

      password: string;

      email: string;

      telefono:string;
    
      paisId: number;

      nombre: string;

      apellidoPaterno: string;

      apellidoMaterno: string;

      fechaNacimiento: Date;


}

export interface Usuario {
usuarioId:bigint;
username: string;
password:string;
nombre:string;
apellidoPaterno :string;
apellidoMaterno:string;
fechaNacimiento: Date | string;
registradoEn:Date | string;
}