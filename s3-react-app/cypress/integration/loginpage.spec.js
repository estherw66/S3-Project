describe("login functionality", () => {
    it('successful login', () => {
        cy.visit('/')
    
        /* ==== Generated with Cypress Studio ==== */
        cy.get('.nav-btn-link').click();
        cy.get('[type="text"]').clear();
        cy.get('[type="text"]').type('JeremyBolm');
        cy.get('[type="password"]').clear();
        cy.get('[type="password"]').type('ToucheAmore');
        cy.get('button').click();
        /* ==== End Cypress Studio ==== */
    })
})