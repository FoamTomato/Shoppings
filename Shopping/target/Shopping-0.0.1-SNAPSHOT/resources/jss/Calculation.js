function calculation(w, c, s) {
    let wk = w / 1000, freight;
    // 运输方式为英国经济专线
    if (s === "cm_hm_yg") {
        if (c === "UK"  || c === "GB") {
            if (0 < w <= 2000) {
                freight = 24 + wk * 30;
            } else {
                freight = 24 + wk * 30;
            }
        } else {
            freight = 0;
        }
        // 运输方式为线下平邮(带电)
    } else if (s === "cm_dd_sz" || s === "cm_sx_sz") {
        if (c === "DE") {
            if (w <= 30) {
                freight = 7.36
            } else if (30 < w <= 80) {
                freight = 7.36 + (w - 30) / 1000 * 71.63;
            } else {
                freight = 7.36 + (w - 30) / 1000 * 52.53;
            }
        } else if (c === "PL") {
            if (w <= 30) {
                freight = 6.82
            } else if (30 < w <= 80) {
                freight = 6.82 + (w - 30) / 1000 * 67.26
            } else {
                freight = 6.82 + (w - 30) / 1000 * 49.67
            }
        } else if (c === "FI") {
            if (w <= 30) {
                freight = 7.50
            } else if (30 < w <= 80) {
                freight = 7.50 + (w - 30) / 1000 * 73.38
            } else {
                freight = 7.50 + (w - 30) / 1000 * 53.95
            }
        } else if (c === "JP") {
            if (w <= 30) {
                freight = 7.00
            } else if (30 < w && w < 80) {
                freight = 7.00 + (w - 30) / 1000 * 56.81
            } else {
                freight = 7.00 + (w - 30) / 1000 * 37.38
            }
        } else if (c === "FR") {
            if (w <= 30) {
                freight = 7.44
            } else if (30 < w <= 80) {
                freight = 7.44 + (w - 30) / 1000 * 73.36
            } else {
                freight = 7.44 + (w - 30) / 1000 * 54.14
            }
        } else if (c === "CA") {
            if (w <= 30) {
                freight = 7.59
            } else if (30 < w <= 80) {
                freight = 7.59 + (w - 30) / 1000 * 76.40
            } else {
                freight = 7.59 + (w - 30) / 1000 * 56.97
            }
        } else if (c === "US") {
            if (w <= 30) {
                freight = 7.21
            } else if (30 < w <= 80) {
                freight = 7.21 + (w - 30) / 1000 * 74.96
            } else {
                freight = 7.21 + (w - 30) / 1000 * 56.79
            }
        } else if (c === "NO") {
            if (w <= 30) {
                freight = 7.42
            } else if (30 < w <= 80) {
                freight = 7.42 + (w - 30) / 1000 * 71.62
            } else {
                freight = 7.42 + (w - 30) / 1000 * 52.28
            }
        } else if (c === "PT") {
            if (w <= 30) {
                freight = 8.00
            } else if (30 < w <= 80) {
                freight = 8.00 + (w - 30) / 1000 * 78.00
            } else {
                freight = 8.00 + (w - 30) / 1000 * 57.50
            }
        } else if (c === "SE") {
            if (w <= 30) {
                freight = 7.37
            } else if (30 < w <= 80) {
                freight = 7.37 + (w - 30) / 1000 * 71.04
            } else {
                freight = 7.37 + (w - 30) / 1000 * 51.82
            }
        } else if (c === "AU") {
            if (w <= 30) {
                freight = 7.45
            } else if (30 < w <= 80) {
                freight = 7.45 + (w - 30) / 1000 * 71.62
            } else {
                freight = 7.45 + (w - 30) / 1000 * 52.19
            }
        } else if (c === "CH") {
            if (w <= 30) {
                freight = 7.45
            } else if (30 < w <= 80) {
                freight = 7.45 + (w - 30) / 1000 * 71.80
            } else {
                freight = 7.45 + (w - 30) / 1000 * 52.37
            }
        } else if (c === "ES") {
            if (w <= 30) {
                freight = 7.52
            } else if (30 < w <= 80) {
                freight = 7.52 + (w - 30) / 1000 * 73.88
            } else {
                freight = 7.52 + (w - 30) / 1000 * 54.45
            }
        } else if (c === "BE") {
            if (w <= 30) {
                freight = 7.41
            } else if (30 < w <= 80) {
                freight = 7.41 + (w - 30) / 1000 * 70.72
            } else {
                freight = 7.41 + (w - 30) / 1000 * 51.32
            }
        } else if (c === "IL") {
            if (w <= 30) {
                freight = 7.08
            } else if (30 < w <= 80) {
                freight = 7.08 + (w - 30) / 1000 * 73.36
            } else {
                freight = 7.08 + (w - 30) / 1000 * 55.49
            }
        } else if (c === "BR") {
            if (w <= 30) {
                freight = 7.00
            } else if (30 < w <= 80) {
                freight = 7.00 + (w - 30) / 1000 * 87.37
            } else {
                freight = 7.00 + (w - 30) / 1000 * 71.35
            }
        } else if (c === "IT") {
            if (w <= 30) {
                freight = 6.83
            } else if (30 < w <= 80) {
                freight = 6.83 + (w - 30) / 1000 * 67.83
            } else {
                freight = 6.83 + (w - 30) / 1000 * 50.24
            }
        } else if (c === "UK" || c === "GB") {
            if (w <= 30) {
                freight = 7.49
            } else if (30 < w <= 80) {
                freight = 7.49 + (w - 30) / 1000 * 73.11
            } else {
                freight = 7.49 + (w - 30) / 1000 * 53.68
            }
        } else if (c === "MX") {
            if (w <= 30) {
                freight = 6.37
            } else if (30 < w <= 80) {
                freight = 6.37 + (w - 30) / 1000 * 72.94
            } else {
                freight = 6.37 + (w - 30) / 1000 * 57.63
            }
        } else if (c === "IE") {
            if (w <= 30) {
                freight = 8.00
            } else if (30 < w <= 80) {
                freight = 8.00 + (w - 30) / 1000 * 73.00
            } else {
                freight = 8.00 + (w - 30) / 1000 * 53.00
            }
        } else if (c === "UA") {
            if (w <= 30) {
                freight = 6.46
            } else if (30 < w <= 80) {
                freight = 6.46 + (w - 30) / 1000 * 69.00
            } else {
                freight = 6.46 + (w - 30) / 1000 * 52.92
            }
        } else if (c === "SA") {
            if (w <= 30) {
                freight = 7.00
            } else if (30 < w <= 80) {
                freight = 7.00 + (w - 30) / 1000 * 100
            } else {
                freight = 7.00 + (w - 30) / 1000 * 100
            }
        } else {
            freight = 0        }
        freight = freight.toFixed(2)
    } else if (s === "ghxb_dd_sz" || s === "ghxb_sg_sz") {
        if (c === "DE") {
            if (w < 150) {
                freight = wk * 60.00 + 15.00
            } else if (150 <= w < 300) {
                freight = wk * 53.00 + 16.00
            } else {
                freight = wk * 48.00 + 17.00
            }
        } else if (c === "JP") {
            if (w <= 150) {
                freight = wk * 40.00 + 23.00
            } else if (150 < w <= 300) {
                freight = wk * 40.00 + 22.00
            } else {
                freight = (wk * 34.00) + 23.00
            }
        } else if (c === "PL") {
            if (w <= 150) {
                freight = wk * 65.00 + 13.00
            } else if (150 < w <= 300) {
                freight = wk * 60.00 + 13.00
            } else {
                freight = wk * 52.00 + 14.50
            }
        } else if (c === "FR") {
            if (w < 150) {
                freight = wk * 68.00 + 12.00
            } else {
                freight = wk * 58.00 + 13.00
            }
        } else if (c === "FI") {
            if (w <= 150) {
                freight = wk * 65.00 + 21.50
            } else if (150 < w <= 300) {
                freight = wk * 60.00 + 22.00
            } else {
                freight = wk * 52.00 + 23.00
            }
        } else if (c === "CA") {
            if (w < 150) {
                freight = wk * 70.00 + 15.80
            } else if (150 <= w < 300) {
                freight = wk * 60.00 + 16.30
            } else {
                freight = wk * 54.00 + 18.00
            }
        } else if (c === "US") {
            freight = wk * 53.00 + 18.00
        } else if (c === "NO") {
            if (w < 150) {
                freight = wk * 55.00 + 17.00
            } else if (150 <= w < 300) {
                freight = wk * 50.00 + 17.50
            } else {
                freight = wk * 48.00 + 18.00
            }
        } else if (c === "PT") {
            if (w < 150) {
                freight = wk * 50.00 + 17.50
            } else {
                freight = wk * 52.00 + 18.00
            }
        } else if (c === "SE") {
            if (w < 150) {
                freight = wk * 57.00 + 22.50
            } else if (150 <= w < 300) {
                freight = wk * 54.00 + 22.50
            } else {
                freight = wk * 48.00 + 23.00
            }
        } else if (c === "AU") {
            if (w < 150) {
                freight = wk * 65.00 + 14.50
            } else {
                freight = wk * 54.00 + 16.00
            }
        } else if (c === "CH") {
            if (w < 300) {
                freight = wk * 52.00 + 23.00
            } else {
                freight = wk * 48.00 + 23.50
            }
        } else if (c === "ES") {
            freight = wk * 50.00 + 18.00
        } else if (c === "BE") {
            if (w < 150) {
                freight = wk * 57.00 + 22.50
            } else if (150 <= w < 300) {
                freight = wk * 52.00 + 22.50
            } else {
                freight = wk * 48.00 + 23.00
            }
        } else if (c === "IL") {
            if (w < 150) {
                freight = wk * 61.00 + 16.00
            } else if (150 <= w < 300) {
                freight = wk * 57.00 + 16.50
            } else {
                freight = wk * 53.00 + 17.00
            }
        } else if (c === "BR") {
            if (w <= 300) {
                freight = wk * 85.00 + 15.00
            } else {
                freight = wk * 74.00 + 17.00
            }
        } else if (c === "IT") {
            if (w < 150) {
                freight = wk * 55.00 + 22.00
            } else if (150 <= w < 300) {
                freight = wk * 49.00 + 22.50
            } else {
                freight = wk * 47.00 + 23.00
            }
        } else if (c === "UK"  || c === "GB") {
            if (w < 150) {
                freight = wk * 52.00 + 15.50
            } else if (150 <= w < 300) {
                freight = wk * 51.00 + 15.50
            } else {
                freight = wk * 50.00 + 15.50
            }
        } else if (c === "MX") {
            if (w < 150) {
                freight = wk * 75.00 + 19.50
            } else if (150 <= w < 300) {
                freight = wk * 75.00 + 17.50
            } else {
                freight = wk * 73.00 + 17.50
            }
        } else if (c === "IE") {
            if (w < 150) {
                freight = wk * 42.50 + 25.50
            } else {
                freight = wk * 49.00 + 26.00
            }
        } else if (c === "UA") {
            if (w < 150) {
                freight = wk * 53.00 + 16.30
            } else if (150 <= w < 300) {
                freight = wk * 53.00 + 16.00
            } else {
                freight = wk * 50.00 + 16.50
            }
        } else if (c === "SA") {
            freight = wk * 122.00 + 12.50
        } else {
            freight = 0
        }
        freight = (freight * 0.92).toFixed(2)
    } else if (s === "cm_rb_shhm") {
        if (c === "JP") {
            if (w < 500) {
                freight = 40.00
            } else {
                freight = 40.00 + Math.ceil((w - 500) / 500) * 10
            }
        } else {
            freight = 0
        }
    } else if (s === "cm_dg_shhm") {
        if (c === "DE") {
            freight = wk * 30.00 + 21
        } else {
            freight = 0
        }
    } else if (s === "cm_eub_szyjdd") {
        if (c === "AU" || c === "FR" || c === "SE" || c === "DE") {
            freight = wk * 60 + 19
        } else if (c === "IE" || c === "FI") {
            freight = wk * 65 + 25
        } else if (c === "BR") {
            freight = wk * 80 + 25
        } else if (c === "BE" || c === "PL" || c === "CH" || c === "IT") {
            freight = wk * 60 + 25
        } else if (c === "CA" || c === "NO" || c === "PT") {
            freight = wk * 65 + 19
        } else if (c === "US") {
            freight = wk * 65 + 15
        } else if (c === "JP") {
            freight = wk * 40 + 15
        } else if (c === "MX") {
            freight = wk * 85 + 25
        } else if (c === "ES") {
            freight = wk * 60 + 14
        } else if (c === "IL") {
            freight = wk * 60 + 17
        } else if (c === "UK"  || c === "GB") {
            if (w < 500) {
                freight = wk * 55 + 18
            } else if (500 <= w < 2000) {
                freight = wk * 45 + 25
            } else {
                freight = wk * 45 + 35
            }
        } else if (c === "SA") {
            freight = wk * 50 + 26
        } else {
            freight = 0
        }
        if (c === "US") {
            freight = (freight * 0.92).toFixed(2)
        } else if (c === "BR") {
            freight = (freight * 0.98).toFixed(2)
        } else {
            freight = (freight * 0.95).toFixed(2)
        }
    } else if (s === "cm_eub_szyj") {
        if (c === "IE" || c === "FI") {
            freight = wk * 65 + 25
        } else if (c === "BE" || c === "PL" || c === "IT" || c === "CH") {
            freight = wk * 60 + 25
        } else if (c === "DE" || c === "FR" || c === "AU" || c === "SE") {
            freight = wk * 60 + 19
        } else if (c === "CA" || c === "PT" || c === "NO") {
            freight = wk * 65 + 19
        } else if (c === "US") {
            freight = wk * 64 + 15
        } else if (c === "BR") {
            freight = wk * 80 + 25
        } else if (c === "MX") {
            freight = wk * 85 + 25
        } else if (c === "JP") {
            freight = wk * 40 + 15
        } else if (c === "SA") {
            freight = wk * 50 + 26
        } else if (c === "IL") {
            freight = wk * 60 + 17
        } else if (c === "ES") {
            freight = wk * 60 + 14
        } else if (c === "UK" || c === "GB") {
            if (w < 500) {
                freight = wk * 55 + 18
            } else if (500 <= w < 2000) {
                freight = wk * 45 + 25
            } else {
                freight = wk * 45 + 35
            }
        } else {
            freight = 0
        }
        if (c === "US") {
            freight = (freight * 0.92).toFixed(2)
        } else {
            freight = (freight * 0.95).toFixed(2)
        }
        freight = (freight * 0.85).toFixed(2)
    } else if (s === "cm_jnd_jy") {
        if (c === "CA") {
            if (50 <= w <= 300) {
                freight = 25.80 + wk * 68.00
            } else if (300 < w <= 500) {
                freight = 27.50 + wk * 59.50
            } else if (500 < w <= 2000) {
                freight = 33.50 + wk * 55.50
            } else {
                freight = 38.50 + wk * 55.50
            }
        } else {
            freight = 0
        }
        freight = (freight * 0.87).toFixed(2)
    } else if (s === "xn_fg_hm") {
        if (c === "FR") {
            freight = 25 + wk * 42
        } else {
            freight = 0
        }
    } else if (s === "cm_esdd_hh") {
        if (c === "ES") {
            freight = 17 + wk * 49
        } else {
            freight = 0
        }
    } else if (s === "cm_it_ft") {
        if (c === "IT") {
            if (w < 1000) {
                freight = 24 + wk * 32
            } else {
                freight = 26 + wk * 32
            }
        } else {
            freight = 0
        }
    } else if (s === "cm_ydl_ft") {
        if (c === "IT") {
            freight = 89 + wk * 7.5
        } else {
            freight = 0
        }
    } else if (s === "xn_dg_hm") {
        if (c === "DE") {
            freight = 22 + wk * 42
        } else {
            freight = 0
        }
    } else if (s === "cm_dgdd_bx") {
        if (c === "DE") {
            freight = wk * 48 + 22
        } else {
            freight = 0
        }
    } else {
        freight = 0
    }
    return freight
}

calculation("", "", "");

// {"线下平邮(带电)": "cm_dd_sz", "线下平邮(普货)": "cm_sx_sz", "线下挂号(普货)": "ghxb_sg_sz", "线下挂号(带电)": "ghxb_dd_sz",
//  "线下E邮宝(带电)": "cm_eub_szyjdd", "线下E邮宝(普货)": "cm_eub_szyj", "日本专线": "cm_rb_shhm",
//  "德国专线": "cm_dg_shhm", "英国经济专线": "cm_hm_yg", "加拿大专线": "cm_jy_jnd", "加拿大带电专线": "cm_jnd_jy",
//  "法国带电专线": "xn_fg_hm", "西班牙带电专线": "cm_esdd_hh", "意大利专线": "cm_it_ft", "意大利专线平邮": "cm_ydl_ft",
//  "德国带线专线": "xn_dg_hm", "德国带电专线挂号": "cm_dgdd_bx"}