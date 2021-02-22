import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthLoginInfo} from '../../../dto/login-info';
import {AuthService} from '../../../service/auth.service';
import {TokenStorageService} from '../../../auth/token-storage.service';
import {AppComponent} from '../../../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private appComp: AppComponent) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.appComp.isLoggedIn = true;
    }
  }


  onSubmit(): void {

    this.loginInfo = new AuthLoginInfo(
      this.form.username, this.form.password
    );
    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveUsername(data.username);

        this.isLoggedIn = true;

        this.appComp.changeToLoggedIn();
        this.isLoginFailed = false;
        this.roles = this.tokenStorage.getAuthorities();


      }, error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
