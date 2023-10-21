import { NgModule } from '@angular/core';
import {AuthModule, LogLevel} from 'angular-auth-oidc-client';
import {environment} from '../../environments/environment';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: environment.authority,
            // redirectUrl: window.location.origin,
            redirectUrl: environment.redirectUrl,
            clientId: environment.clientId,
            scope: 'openid profile offline_access email',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            logLevel: LogLevel.Error,
            secureRoutes: [environment.secureRoutes],
            customParamsAuthRequest: {
              audience: environment.audience
            }
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
