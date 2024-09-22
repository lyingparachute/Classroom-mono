class UrlUtils {
    getUrlFromPath(path: string): URL | null {
        try {
            return new URL(path);
        } catch (e) {
            return null;
        }
    }

    getAsCssUrl(path: string): string {
        return `url(${path})`;
    }

    appendQueryString(urlString: string, name: string, value: string): string {
        const url = this.getUrlFromPath(urlString);
        if (url === null) return urlString;

        url.searchParams.append(name, value);

        return url.toString();
    }
}

export const urlUtils: UrlUtils = new UrlUtils();
