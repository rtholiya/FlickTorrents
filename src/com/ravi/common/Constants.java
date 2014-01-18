package com.ravi.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ravi.torrentino.R;

public class Constants {
	
public static final List<String> SUPPORTED_SITES =	Arrays.asList(
    "ake.fm",
	"publichd.eu",
	"coda.fm",
	"movietorrents.eu",
	"bt-chat.com",
	"newtorrents.info",
	"archive.org",
	"linuxtracker.org",
	"mininova.org",
	"swesub.tv",
	"kickasstorrents.com",
	"thepiratebay.se",
	"rarbg.com",
	"kat.ph",
	"torlock.com",
	"extratorrent.com",
	"torrentbit.net",
	"vertor.com",
	"thepiratebay.se",
	"fenopy.se",
	"torrenthound.com",
	"yourbittorrent.com",
	"monova.org",
	"h33t.com",
	"torrentreactor.net",
	"fulldls.com",
	"seedpeer.me",
	"torrentzap.com",
	"torrentdownloads.me",
	"torrents.net",
	"torrentfunk.com",
	"limetorrents.com",
	"bitsnoop.com",
	"torrentcrazy.com");

public static  Map<String,Integer> SUPPORTED_SITES_IMG = new HashMap<String, Integer>(){{
	put("ake.fm",R.drawable.linuxtracker);
	put("publichd.eu",R.drawable.phd);
	put("coda.fm",R.drawable.coda);
    put("movietorrents.eu",R.drawable.movietorrent);
	put("bt-chat.com",R.drawable.bt_chat);
	put("newtorrents.info",R.drawable.newtorrent);
	put("archive.org",R.drawable.archive);
	put("linuxtracker.org",R.drawable.linuxtracker);
	put("mininova.org",R.drawable.mininova);
	put("swesub.tv",R.drawable.swesub);
	put("kickasstorrents.com",R.drawable.kickasstorrent);
	put("thepiratebay.se",R.drawable.thepiratebay);
	put("rarbg.com",R.drawable.rarbg);
	put("kat.ph",R.drawable.kat);
	put("torlock.com",R.drawable.torlock);
	put("extratorrent.com",R.drawable.extratorrent);
	put("torrentbit.net",R.drawable.torrentbit);
	put("vertor.com",R.drawable.vertor);
	put("thepiratebay.se",R.drawable.thepiratebay);
	put("fenopy.se",R.drawable.fenoy);
	put("torrenthound.com",R.drawable.torrenthound);
	put("yourbittorrent.com",R.drawable.yourbittorrent);
	put("monova.org",R.drawable.monova);
	put("h33t.com",R.drawable.h33t);
	put("torrentreactor.net",R.drawable.torrentreactor);
	put("fulldls.com",R.drawable.fulldls);
	put("seedpeer.me",R.drawable.seedpeer);
	put("torrentzap.com",R.drawable.torrentzap);
	put("torrentdownloads.me",R.drawable.torrentdownloads);
	put("torrents.net",R.drawable.torrents);
	put("torrentfunk.com",R.drawable.torrentfunk);
	put("limetorrents.com",R.drawable.limetorrents);
	put("bitsnoop.com",R.drawable.bitsnoop);
	put("torrentcrazy.com",R.drawable.torrentcrazy);
	}};

public static final String movie_byte_array = "movie_byte_array";
public static final String need_refresh = "need_refresh";
}
