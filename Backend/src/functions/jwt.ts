import jwt from 'jsonwebtoken';
import HttpException from '../http-exception.js';

export const jwtSettings = {
  // UWAGA! Nigdy nie umieszczamy bezpośrednio w kodzie, jest to tylko w celach ułatwienia.
  // Sekret powinien być mocną losową wartością i przechowywany poza kodem.
  secret: "SEKRETNA_WARTOSC_JWT",

  // Jest to w celu uproszczenia, w prawdziwej produkcyjnej aplikacji
  // Powinniśmy użyć mniejszego czasu wygaszenia, 
  // Odnawiać sesje jeżeli jest taka potrzeba
  // I zadbać o zapisywanie nieważnych tokenów.
  expiration: "1y"
};

export async function signJWT(data: any) {
  return new Promise<string>((resolve, reject) => {
    jwt.sign(data, jwtSettings.secret, { expiresIn: jwtSettings.expiration }, (err, result) => {
      if (err) {
        return reject(err);
      }
      resolve(result);
    });
  });
}

export async function verifyJWT(token: string) {
  return new Promise<any>((resolve, reject) => {
    jwt.verify(token, jwtSettings.secret, (err, result) => {
      if (err) {
        return reject(new HttpException(401, "Nieprawidłowa sesja, zaloguj się ponownie"));
      }
      resolve(result);
    })
  })
}
