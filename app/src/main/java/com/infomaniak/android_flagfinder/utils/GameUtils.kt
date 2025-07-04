package com.infomaniak.android_flagfinder.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.infomaniak.android_flagfinder.data.AnimationType
import java.util.Locale


object GameUtils {
    const val PartySize = 12

    val CountryCodes = listOf(
        "ad", "ae", "af", "al", "am", "ao", "aq", "ar", "at", "au",
        "az", "ba", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl",
        "bm", "bo", "br", "bs", "bt", "bw", "by", "bz", "ca", "cd",
        "cf", "ch", "ci", "cl", "cm", "cn", "co", "cr", "cu", "cv",
        "cy", "cz", "de", "dj", "dk", "dm", /*"do",*/ "dz", "ec", "ee",
        "eg", "er", "es", "et", "fi", "fj", "fr", "ga", "gb", "ge",
        "gf", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs",
        "gt", "gw", "gy", "hk", "hn", "hr", "ht", "hu", "id", "ie",
        "il", "im", "in", "iq", "ir", "is", "it", "jm", "jo", "jp",
        "ke", "kg", "kh", "km", "kp", "kr", "kw", "ky", "kz", "la",
        "lb", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma",
        "mc", "md", "me", "mf", "mg", "mk", "ml", "mm", "mn", "mo",
        "mq", "mr", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na",
        "nc", "ne", "ng", "ni", "nl", "no", "np", "nz", "om", "pa",
        "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pr", "ps", "pt",
        "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sc", "sd",
        "se", "sg", "sh", "si", "sk", "sl", "sm", "sn", "so", "sr",
        "ss", "sv", "sy", "sz", "td", "tg", "th", "tj", "tm", "tn",
        "tr", "tw", "tz", "ua", "ug", "us", "uy", "uz", "va", "ve",
        "vn", "wf", "xk", "ye", "yt", "za", "zm", "zw",
    )

    val AllCodes = arrayOf(
        "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar",
        "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be",
        "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq",
        "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd",
        "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr",
        "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm",
        "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi",
        "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gb-eng", "gb-nir", "gb-sct",
        "gb-wls", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn",
        "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm",
        "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io",
        "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg",
        "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la",
        "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly",
        "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm",
        "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw",
        "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl",
        "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg",
        "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py",
        "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd",
        "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so",
        "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf",
        "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt",
        "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va",
        "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "xk", "ye",
        "yt", "za", "zm", "zw",
    )

    fun getCountryName(countryCode: String): String {
        return Locale("", countryCode).displayCountry
    }

    @SuppressLint("DiscouragedApi")
    @Composable
    fun getFlagDrawable(flag: String, context: Context = LocalContext.current): Int {
        Log.i("Flag", "getFlagDrawable: flag=$flag")
        return context.resources.getIdentifier(flag, "drawable", context.packageName)
    }

    fun getResultType(flag: String, answered: String?, correctFlag: String): AnimationType {
        return when {
            answered == null -> AnimationType.NONE
            answered == correctFlag && flag == answered -> AnimationType.CORRECT
            flag == answered -> AnimationType.WRONG
            flag == correctFlag -> AnimationType.CORRECT
            else -> AnimationType.BLURRED
        }
    }
}
