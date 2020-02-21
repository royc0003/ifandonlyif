import scrapy
from .item import DealItem
from scrapy.loader import ItemLoader
#scrapy crawl deals -o data.json

class DealsSpider(scrapy.Spider):
    name='deals'

    start_urls = [
        'https://singpromos.com/tag/1-for-1/'
    ]

    def parse(self, response):
        for deal in response.xpath("//article[@class='mh-loop-item clearfix']"):
            '''
            yield{
                'name': deal.xpath(".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/h3/a/text()").extract_first(),
                'start': deal.xpath(".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden dtstart']/span[@class='value-title']/@title").extract_first(),
                'end': deal.xpath(".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden dtend']/span[@class='value-title']/@title").extract_first(),
                'image': deal.xpath(".//div[@class='mh-loop-thumb']/a/img/@src").extract_first(),
                'vendor': deal.xpath(".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden location']/text()").extract_first(),
                'summary': deal.xpath(".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden summary']/text()").extract_first(),
            }'''
            i = ItemLoader(item=DealItem(), selector=deal)
            i.add_xpath('name', ".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/h3/a/text()")
            i.add_xpath('start', ".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden dtstart']/span[@class='value-title']/@title")
            i.add_xpath('end', ".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden dtend']/span[@class='value-title']/@title")
            i.add_xpath('image', ".//div[@class='mh-loop-thumb']/a/img/@src"),
            i.add_xpath('vendor', ".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden location']/text()")
            i.add_xpath('summary', ".//div[@class='mh-loop-content clearfix']/header[@class='mh-loop-header']/div[@class='mh-meta mh-loop-meta']/span[@class='mh-meta-date updated']/span[@class='hidden summary']/text()")
            yield i.load_item()

            next_page = response.xpath("//div[@class='mh-loop-pagination clearfix']/a[@class='next page-numbers']/@href").extract_first()
            print(next_page)
            if next_page is not None:
                #next_page_link = response.urljoin(next_page)
                yield scrapy.Request(url=next_page, callback=self.parse)
