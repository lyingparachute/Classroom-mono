import {urlUtils} from './url.utils';

describe('url.utils', () => {
    describe('#getUrlFromPath', () => {
        it('should return URL from correct path', () => {
            expect(urlUtils.getUrlFromPath('http://test.com')).not.toBe(null);
            expect(urlUtils.getUrlFromPath('https://test.com')).not.toBe(null);
            expect(urlUtils.getUrlFromPath('https://test.com?param1=val1&param2=val2')).not.toBe(null);
        });

        it('should return null if path is not correct', () => {
            expect(urlUtils.getUrlFromPath('xxx')).toBe(null);
            expect(urlUtils.getUrlFromPath('https/test.com')).toBe(null);
            expect(urlUtils.getUrlFromPath('http/test.com')).toBe(null);
            expect(urlUtils.getUrlFromPath('https//:test.com')).toBe(null);
            expect(urlUtils.getUrlFromPath('http//:test.com')).toBe(null);
            expect(urlUtils.getUrlFromPath('http.test.com')).toBe(null);
            expect(urlUtils.getUrlFromPath('https.test.com')).toBe(null);
        });
    });

    describe('#appendQueryString', () => {
        it('should return input on incorrect url', () => {
            expect(urlUtils.appendQueryString('xxx', 'name', 'value')).toBe('xxx');
        });

        it('should append query string to correct url', () => {
            expect(urlUtils.appendQueryString('https://test.com/test?param1=val1', 'name', 'value')).toBe(
                'https://test.com/test?param1=val1&name=value'
            );
        });
    });
});
