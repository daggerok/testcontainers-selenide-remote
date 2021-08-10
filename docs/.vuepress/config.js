const { name: title, description, repository } = require(`${process.cwd()}/package.json`)
const base = process.env.BASE || '/';

module.exports = {
  description,
  title,
  base,
  head: [
    ['link', { rel: 'icon', href: base + 'favicon.ico', }],
    ['meta', { name: 'apple-mobile-web-app-capable', content: 'yes' }],
    ['meta', { name: 'apple-mobile-web-app-status-bar-style', content: 'black' }],
  ],
  themeConfig: {
    repo: repository.url,
    docsDir: repository.directory,
    docsBranch: repository.docsBranch,
    searchPlaceholder: 'Search...',
    activeHeaderLinks: false,
    displayAllHeaders: true,
    lastUpdated: true,
    editLinks: true,
    sidebar: [
      '/',                    // -> ./docs/
      // '/getting-started/', // -> ./docs/getting-started/
      // ...
    ],
    sidebarDepth: 5,
    collapsable: false,
  },
  plugins: [
    '@vuepress/plugin-back-to-top',
    '@vuepress/plugin-medium-zoom',
  ],
  markdown: {
    extendMarkdown: md => {
      md.use(require('markdown-it-vuepress-code-snippet-enhanced'));
    },
  },
};
