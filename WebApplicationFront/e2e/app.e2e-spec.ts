import { WebApplicationFrontPage } from './app.po';

describe('web-application-front App', () => {
  let page: WebApplicationFrontPage;

  beforeEach(() => {
    page = new WebApplicationFrontPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
