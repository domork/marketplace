import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {finalize} from 'rxjs/operators';
import {TokenStorageService} from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = '71.5% market';
  private roles: string[];
  private authority: string;

  constructor(private tokenStorage: TokenStorageService, private router: Router) {
  }

  isSignedOut = false;
  isLoggedIn = false;

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          return false;
        }
        this.authority = 'user';
        this.isLoggedIn = true;
        this.isSignedOut = false;
        return true;
      });
    }
  }

  logout(): void {
    if (this.tokenStorage.getUsername()) {
      this.tokenStorage.signOut();
      this.router.navigate(['/']);
      this.isSignedOut = true;
      this.isLoggedIn = false;
    }
  }

  changeToLoggedIn(): void {
    this.isLoggedIn = true;
    this.isSignedOut = false;
  }
}
