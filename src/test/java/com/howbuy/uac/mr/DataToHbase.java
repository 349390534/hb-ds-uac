package com.howbuy.uac.mr;

import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-2-21 下午1:35
 * @modify
 * @since JDK1.6
 * 类描述
 *
 * @version [PZoom-V2.0]
 * @author  liuxiaochen
 *
 * 修改标识：
 * 修改描述：
 *
 */
public class DataToHbase
{
    public static class MapHbase extends Mapper<Object, Text, Text, IntWritable>
    {
        private final static IntWritable one = new IntWritable(1) ;
        private Text text = new Text() ;

        public void map(Object key, Text value, Context context)	throws IOException, InterruptedException
        {
            String line = value.toString() ;
            StringTokenizer stiz = new StringTokenizer(line) ;
            while(stiz.hasMoreTokens())
            {
                text.set(stiz.nextToken()) ;
                context.write(text, one) ;
            }
        }

    }

    public static class ReduceHbase extends TableReducer<Text, IntWritable, Text>
    {

        public void reduce(Text text, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException
        {
            Put put = null ;
            String rowkey = "" ;
            int sum = 0 ;
            for(IntWritable i : values)
            {
                sum += i.get() ;
            }
            rowkey = String.valueOf(new Random(100)) ;
            put = new Put(rowkey.getBytes()) ;
            put.add("content".getBytes(), "text".getBytes(), (sum+"").getBytes()) ;
            put.add("content".getBytes(), "sum".getBytes(), text.toString().getBytes()) ;

            context.write(new Text("xchen1"), put) ;
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
    {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","192.168.1.30,192.168.1.31,192.168.1.32");
        conf.set("hbase.zookeeper.property.clientPort", "2222");
        Job job;
        job = new Job(conf, "Runner"); //

        Path in = new Path("D:/testFile/mr2/a.txt") ;
        job.setJarByClass(DataToHbase.class) ;
        job.setMapOutputKeyClass(Text.class) ;
        job.setMapOutputValueClass(IntWritable.class) ;

        job.setMapperClass(MapHbase.class) ;
        job.setReducerClass(ReduceHbase.class) ;
        FileInputFormat.setInputPaths(job, in);
//		org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.setOutputPath(job, new Path("D:/testFile/mr1/" + new Date().getTime())) ;


        TableMapReduceUtil.initTableReducerJob("xchen1", ReduceHbase.class, job) ;
//		com.hbase.TableMapReduceUtil.initMulitTableReducerJob("xchen1",ReduceHbase.class, job, null, null, null,
//				null, true);

        job.waitForCompletion(true);
    }
}
