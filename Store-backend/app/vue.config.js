module.exports = {
    publicPath: './',
    outputDir: 'dist',

    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:18085',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
}