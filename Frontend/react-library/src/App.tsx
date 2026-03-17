import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { Footer } from './layouts/NavbarAndFooter/Footer';
import { HomePage } from './layouts/HomePage/HomePage';
import { SearchBooksPage } from './layouts/SearchBooksPage/SearchBooksPage';
import { Redirect, Route, Switch } from 'react-router-dom';
import { BookCheckoutPage } from './layouts/BookCheckoutPage/BookCheckoutPage';

export const App = () => {
  //the divs make sure the Footer component stays at the bottom of screen
  //Ex: when no results for searched book, footer should stay at bottom
  //flex-grow-1 makes the div stretch to rest of screen where there's space
  //Ex:Full page is 1000px, Navbar is 100px, Footer is 100px, div will take 800px.
  //The contents will sit inside the div or make the div stretch to fit it
  return (
    <div className='d-flex flex-column min-vh-100'>
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
          <Route path='/checkout/:bookId'>
            <BookCheckoutPage />
          </Route>
        </Switch>
      </div>
      <Footer />
    </div>
  );
};
