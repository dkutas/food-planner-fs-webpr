import {Injectable} from '@angular/core';
import Keycloak, {KeycloakProfile} from 'keycloak-js';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak: Keycloak | undefined;
  private _profile: KeycloakProfile | undefined;

  get keycloak() {
    if (!this._keycloak)
      this._keycloak = new Keycloak({
        url: environment.keycloak.url,
        realm: environment.keycloak.realm,
        clientId: environment.keycloak.clientId
      });

    return this._keycloak;
  }

  get profile() {
    return this._profile;
  }

  constructor() {
  }

  async initializeKeycloak() {
    console.log("Initializing keycloak....")
    const authenticated = await this.keycloak?.init({
      onLoad: "login-required"
    })

    if(authenticated) {
      console.log("User authenticated...")
      this._profile = await this.keycloak?.loadUserProfile()
    }
  }

  login() {
    this.keycloak?.login();
  }

  logout() {
    this.keycloak?.logout();
  }

}
