import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { SignupaddressComponent } from './signupaddress/signupaddress.component';
import { FormsModule } from '@angular/forms'
import { RestaurantlistComponent } from './restaurantlist/restaurantlist.component';
import { FooditemlistComponent } from './fooditemlist/fooditemlist.component';
import { CartComponent } from './cart/cart.component';
import { HistoryComponent } from './history/history.component';
import { AddrestaurantComponent } from './addrestaurant/addrestaurant.component';
import { ModifyrestaurantComponent } from './modifyrestaurant/modifyrestaurant.component';
import { ViewordersComponent } from './vieworders/vieworders.component';

import { BlockComponent } from './block/block.component';
import { HttpClientModule } from '@angular/common/http';
import { ForgotComponent } from './forgot/forgot.component';
import { OtpComponent } from './otp/otp.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { FooterComponent } from './footer/footer.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { ContactusComponent } from './contactus/contactus.component';
import { TermsandconditionsComponent } from './termsandconditions/termsandconditions.component';
import { DeliveryComponent } from './delivery/delivery.component';
import { PaymentComponent } from './payment/payment.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { ManagerestaurantComponent } from './managerestaurant/managerestaurant.component';
import { CustomerlistComponent } from './customerlist/customerlist.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    SignupaddressComponent,
    RestaurantlistComponent,
    FooditemlistComponent,
    CartComponent,
    HistoryComponent,
    AddrestaurantComponent,
    ModifyrestaurantComponent,
    ViewordersComponent,
    CustomerlistComponent,
    BlockComponent,
    DeliveryComponent,
    PaymentComponent,
    ForgotComponent,
    OtpComponent,
    ChangepasswordComponent,
    FooterComponent,
    AboutusComponent,
    ContactusComponent,
    TermsandconditionsComponent,
    AdminhomeComponent,
    ManagerestaurantComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
