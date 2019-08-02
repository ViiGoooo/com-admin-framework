package com.admin.framework.component.aria2c.entity;

import lombok.Data;

/**
 * Created by ZSW on 2019/2/25.
 */
@Data
public class TellStatusResult extends Result {

    /**
     * 下载总长度（以字节为单位）。
     */
    private String totalLength;

    /**
     * 已完成下载的长度（以字节为单位）
     */
    private String completedLength;

    /**
     *上载的下载长度（以字节为单位）。
     */
    private String uploadLength;

    /**
     *下载进度的十六进制表示。最高位对应于索引0处的块。任何设置位指示加载的块，而未设置位指示尚未加载和/或丢失的块。最后的任何溢出位都设置为零。当下载尚未开始时，此密钥将不会包含在响应中。
     */
    private String bitfield;

    /**
     *下载速度以字节/秒为单位。
     */
    private String downloadSpeed;

    /**
     *此下载的上载速度以字节/秒为单位。
     */
    private String uploadSpeed;

    /**
     * 信息散列。仅限BitTorrent。
     */
    private String infoHash;

    /**
     * 播种机aria2连接的数量。仅限BitTorrent。
     */
    private String numSeeders;

    /**
     *  true如果本地端点是播种机。否则false。仅限BitTorrent。
     */
    private String seeder;

    /**
     *  片长度，以字节为单位
     */
    private String pieceLength;

    /**
     *  件数。
     */
    private String numPieces;

    /**
     *  aria2连接的对等体/服务器数量。
     */
    private String connections;

    /**
     *  此项目的最后一个错误的代码（如果有）。该值是一个字符串。错误代码在EXIT STATUS部分中定义。此值仅适用于已停止/已完成的下载。
     */
    private String errorCode;

    /**
     *  希望）人类可读的错误消息 errorCode。
     */
    private String errorMessage;

    /**
     *  由此下载生成的GID列表。例如，当aria2下载Metalink文件时，它会生成Metalink中描述的下载（请参阅 --follow-metalink选项）。
     *  此值对于跟踪自动生成的下载非常有用。如果没有此类下载，则此密钥不会包含在响应中
     */
    private String followedBy;

    /**
     *  反向链接followedBy。包含在其中的下载 followedBy具有此对象的GID following值。
     */
    private String following;

    /**
     *  父下载的GID。
     *  某些下载是另一个下载的一部分。
     *  例如，如果Metalink中的文件具有BitTorrent资源，则“.torrent”文件的下载是该父级的一部分。
     *  如果此下载没有父级，则此密钥不会包含在响应中。
     */
    private String belongsTo;

    /**
     *  保存文件的目录。
     */
    private String dir;

    /**
     *  返回文件列表。此列表的元素与aria2.getFiles()方法中使用的结构相同。
     */
    private String files;

    /**
     *  包含从.torrent（文件）检索的信息的Struct。仅限BitTorrent。它包含以下键。
     */
    private String bittorrent;

}
