import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { Footer } from './layouts/NavbarAndFooter/Footer';
import { HomePage } from './layouts/HomePage/HomePage';
import { SearchBooksPage } from './layouts/SearchBooksPage/SearchBooksPage';
import { Redirect, Route, Switch, useHistory } from 'react-router-dom';
import { BookCheckoutPage } from './layouts/BookCheckoutPage/BookCheckoutPage';

import { Auth0Provider, withAuthenticationRequired } from '@auth0/auth0-react';
import { auth0Config } from './lib/auth0Config';
import LoginPage from './Auth/LoginPage';
import { ReviewListPage } from './layouts/BookCheckoutPage/ReviewListPage/ReviewListPage';
import { ShelfPage } from './layouts/ShelfPage/ShelfPage';
import { MessagesPage } from './layouts/MessagesPage/MessagesPage';

const Auth0ProviderWithHistory = ({ children }: { children: React.ReactNode }) => {
  const history = useHistory();

  const onRedirectCallback = (appState: any) => {
    history.push(appState?.returnTo || "/home");
  };

  return (
    <Auth0Provider
      domain={auth0Config.domain}
      clientId={auth0Config.clientId}
      authorizationParams={{
        redirect_uri: auth0Config.redirectUri,
        audience: auth0Config.audience,
        scope: auth0Config.scope,
      }}
      onRedirectCallback={onRedirectCallback}
    >
      {children}
    </Auth0Provider>
  );
};

const SecureRoute = ({ component, path, ...args }: { component: React.ComponentType<any>, path: string }) => (
  <Route path={path} component={withAuthenticationRequired(component)} {...args} />
);

export const App = () => {
  //the divs make sure the Footer component stays at the bottom of screen
  //Ex: when no results for searched book, footer should stay at bottom
  //flex-grow-1 makes the div stretch to rest of screen where there's space
  //Ex:Full page is 1000px, Navbar is 100px, Footer is 100px, div will take 800px.
  //The contents will sit inside the div or make the div stretch to fit it
  return (
    <div className='d-flex flex-column min-vh-100'>
      <Auth0ProviderWithHistory>
        <Navbar />
        <div className='flex-grow-1'>
          <Switch>
            <Route path='/' exact>
              <Redirect to='/home' />
            </Route>
            <Route path='/home'>
              <HomePage />
            </Route>
            <Route path='/search'>
              <SearchBooksPage />
            </Route>
            <Route path='/reviewlist/:bookId'>
              <ReviewListPage />
            </Route>
            <Route path='/checkout/:bookId'>
              <BookCheckoutPage />
            </Route>
            <Route path='/login' render={() => <LoginPage />} />
            <SecureRoute path='/shelf' component={ShelfPage} />
            <SecureRoute path='/messages' component={MessagesPage} />
          </Switch>
        </div>
        <Footer />
      </Auth0ProviderWithHistory>
    </div>
  );
};
