describe('add employee', () => {
    it('add employee successfull', () => {
        cy.visit('/')

        /* ==== Generated with Cypress Studio ==== */
        cy.get('.nav-btn-link').click();
        cy.get('[type="text"]').clear();
        cy.get('[type="text"]').type('JeremyBolm');
        cy.get('[type="password"]').clear();
        cy.get('[type="password"]').type('ToucheAmore');
        cy.get('button').click();
        cy.get('.header').click();
        cy.get(':nth-child(4) > .side-link > .link').click();
        cy.get('.header > a').click();
        cy.get('#first-name').clear();
        cy.get('#first-name').type('George');
        cy.get('#last-name').clear();
        cy.get('#last-name').type('Stevens');
        cy.get('#phone-number').clear();
        cy.get('#phone-number').type('+31666554477');
        cy.get('#date-of-birth').type("1990-10-31");
        cy.get('#street-name').clear();
        cy.get('#street-name').type('Mozartlaan 41');
        cy.get('#zip-code').clear();
        cy.get('#zip-code').type('5151KA');
        cy.get('#city').clear();
        cy.get('#city').type('Drunen');
        cy.get('#save-changes').click();
        /* ==== End Cypress Studio ==== */
    })
})