import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  host = 'https://vzskf5okii.execute-api.us-east-1.amazonaws.com/prod/api/auth'
  //host = environment.api + '/auth'

  constructor(private http: HttpClient) {
  }

  login(user: any) {
    return this.http.post(this.host + '/signIn', user)
  }

  register() {

  }

}
