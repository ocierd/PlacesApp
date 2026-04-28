import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '@services/auth/auth-service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const tokenData = authService.getToken();
  const newReq = req.clone();
  if(tokenData){
    newReq.headers.append('Authorization',tokenData.token);
  }
  return next(newReq);
};
