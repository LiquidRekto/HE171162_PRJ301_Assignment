/** @type {import('tailwindcss').Config} */

module.exports = {
  content: ["./**/*.{jsp,html,js}"],
  theme: {
    extend: {
        backgroundImage: {
            'login': "url('../../images/login2.jpg');"
      },
    }
  },
  plugins: []
}
