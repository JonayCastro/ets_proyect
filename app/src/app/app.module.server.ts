import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';

/**
 * AppServerModule is the main module for server-side rendering.
 * It imports the AppModule and ServerModule.
 * It bootstraps the AppComponent.
 * This module is used for server-side rendering of the application.
 */

@NgModule({
  imports: [
    AppModule,
    ServerModule,
  ],
  bootstrap: [AppComponent],
})
export class AppServerModule {}
