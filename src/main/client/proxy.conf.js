const PROXY_CONFIG = [
  {
    context: [
      "/game",
      "/table"
    ],
    target: "http://localhost:8080",
    secure: false,
    changeOrigin: true,
    logLevel: "debug"
  }
];

module.exports = PROXY_CONFIG;
