describe('update account info', () => {
    it('update phone number successful', () => {
        cy.visit('/')


        /* ==== Generated with Cypress Studio ==== */
        cy.get('.nav-btn-link').click();
        cy.get('[type="text"]').clear();
        cy.get('[type="text"]').type('JeremyBolm');
        cy.get('[type="password"]').clear();
        cy.get('[type="password"]').type('ToucheAmore');
        cy.get('button').click();
        cy.get('.profile-btn').click();
        cy.get(':nth-child(3) > input').clear();
        cy.get(':nth-child(3) > input').type('+31617491291');
        cy.get('button').click();
        /* ==== End Cypress Studio ==== */
    })
})