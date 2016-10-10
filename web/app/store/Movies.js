Ext.define('Cinema.store.Movies', {
    extend: 'Ext.data.Store',

    alias: 'store.movies',

    id: 'moviesStore',

    autoLoad: true,
    proxy: {
        type: 'rest',
        url: 'cinema/movies/all',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
    
});
